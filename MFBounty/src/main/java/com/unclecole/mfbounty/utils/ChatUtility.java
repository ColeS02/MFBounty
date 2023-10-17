package com.unclecole.mfbounty.utils;

import com.google.common.base.Strings;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ChatUtility {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> color(List<String> messages) {
        List<String> buffered = new ArrayList<>();
        for (String message : messages) {
            buffered.add(color("&r" + message));
        }
        return buffered;
    }

    public static String getProgressBar(int current, int max, int totalBars, String symbol) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat("" + ChatColor.DARK_GREEN + symbol, progressBars)
                + Strings.repeat("" + ChatColor.GRAY + symbol, totalBars - progressBars);
    }

    public static String getCooldownBar(int current, int max) {
        float percent = (float) current / max;
        int progressBars = (int) (15 * percent);

        return Strings.repeat("" + ChatColor.GOLD + "▉", progressBars)
                + Strings.repeat("" + ChatColor.GREEN + "▉", 15 - progressBars);
    }

}

