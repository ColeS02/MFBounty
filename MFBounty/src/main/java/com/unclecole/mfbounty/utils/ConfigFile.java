package com.unclecole.mfbounty.utils;

import com.unclecole.mfbounty.MFBounty;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {

	private File configFile;
	private FileConfiguration config;
	private MFBounty plugin;

	public ConfigFile(String name, MFBounty plugin) {
		this.plugin = plugin;
		this.configFile = new File(this.plugin.getDataFolder(), name);
		if (!this.configFile.exists()) {
			this.configFile.getParentFile().mkdirs();
			if (this.plugin.getResource(name) == null) {
				try {
					this.configFile.createNewFile();
				} catch (IOException ex) {
					Bukkit.getLogger().severe("----------------------");
					Bukkit.getLogger().severe("Failed to create file " + name);
					Bukkit.getLogger().severe("----------------------");
				}
			} else {
				this.plugin.saveResource(name, false);
			}
		}
		this.config = YamlConfiguration.loadConfiguration(this.configFile);
	}

	public void save() {
		try {
			this.getConfig().save(this.configFile);
		} catch (IOException ex) {
			Bukkit.getLogger().severe("Could not save config file " + this.configFile.toString());
			ex.printStackTrace();
		}
	}

	public FileConfiguration getConfig() {
		return config;
	}
}
