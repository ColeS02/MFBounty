package com.unclecole.mfbounty.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.logging.Level;

public enum TL {
	NO_PERMISSION("messages.no-permission", "&cYou don't have the permission to do that."),
	INVALID_AMOUNT("messages.invalid-amount", "&cInvalid Amount <command>"),
	INVALID_PLAYER("messages.invalid-player", "&cInvalid Player <command>"),
	INVALID_COMMAND("messages.invalid-command", "&cInvalid Command <command>"),
	SELF_BOUNTY("messages.self-bounty", "&cYou cannot place a bounty on yourself!"),
	INSUFFICIENT_FUNDS("message.insufficient-funds", "&fInsufficient funds to place this bounty!"),
	BOUNTY_PLACED("messages.bounty-placed", "&6&lBOUNTY &a%player% &fhas placed a bounty on &6%bounty% &ffor &e%amount%"),
	ALREADY_HAS_BOUNTY("messages.already-has-bounty", "&c&lERROR &a%player% already has a bounty greater than &e%amount%"),
	BOUNTY_COLLECTED("messages.bounty-collected", "&6&lBOUNTY &a%player% &fhas collected a bounty placed by &a%bountyOwner%, on %bounty% for &e%amount%");
	private final String path;

	private String def;
	private static ConfigFile config;

	TL(String path, String start) {
		this.path = path;
		this.def = start;
	}

	public String getDefault() {
		return this.def;
	}

	public String getPath() {
		return this.path;
	}

	public void setDefault(String message) {
		this.def = message;
	}

	public void broadcast(PlaceHolder... placeHolders) {
		Bukkit.getOnlinePlayers().forEach(player -> {
			send(player, placeHolders);
		});
	}

	public void send(CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			sender.sendMessage(PlaceholderAPI.setPlaceholders(player, C.color(getDefault())));
		} else {
			sender.sendMessage(C.strip(getDefault()));
		}
	}

	public static void loadMessages(ConfigFile configFile) {
		config = configFile;
		FileConfiguration data = configFile.getConfig();
		for (TL message : values()) {
			if (!data.contains(message.getPath())) {
				data.set(message.getPath(), message.getDefault());
			}
		}
		configFile.save();
	}


	public void send(CommandSender sender, PlaceHolder... placeHolders) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			sender.sendMessage(PlaceholderAPI.setPlaceholders(player, C.color(getDefault(), placeHolders)));
		} else {
			sender.sendMessage(C.strip(getDefault(), placeHolders));
		}
	}

	public static void message(CommandSender sender, String message) {
		sender.sendMessage(C.color(message));
	}

	public static void message(CommandSender sender, String message, PlaceHolder... placeHolders) {
		sender.sendMessage(C.color(message, placeHolders));
	}

	public static void message(CommandSender sender, List<String> message) {
		message.forEach(m -> sender.sendMessage(C.color(m)));
	}

	public static void message(CommandSender sender, List<String> message, PlaceHolder... placeHolders) {
		message.forEach(m -> sender.sendMessage(C.color(m, placeHolders)));
	}

	public static void log(Level lvl, String message) {
		Bukkit.getLogger().log(lvl, message);
	}
}
