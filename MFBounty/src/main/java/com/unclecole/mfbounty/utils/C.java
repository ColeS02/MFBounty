package com.unclecole.mfbounty.utils;

import com.google.common.base.Strings;
import org.bukkit.ChatColor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class C {
	
	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static String color(String s, PlaceHolder... placeHolders) {
		String message = s;
		for (PlaceHolder placeHolder : placeHolders) {
			message = color(message.replace(placeHolder.getPlaceHolder(), placeHolder.getReplace()));
		}
		return message;
	}

	public static List<String> color(List<String> s) {
		List<String> toReturn = new ArrayList<>();
		for (String str : s) {
			toReturn.add(color(str));
		}
		return toReturn;
	}

	public static List<String> color(List<String> messages, PlaceHolder... placeholders) {
		List<String> colored = new ArrayList<>();
		for (String line : messages) {
			String coloredLine = line;
			for (PlaceHolder placeholder : placeholders) {
				coloredLine = color(coloredLine.replace(placeholder.getPlaceHolder(), placeholder.getReplace()));
			}
			colored.add(coloredLine);
		}
		return colored;
	}
	
	public static String strip(String s) {
		return ChatColor.stripColor(s);
	}

	public static String strip(String s, PlaceHolder... placeHolders) {
		String message = s;
		for (PlaceHolder placeHolder : placeHolders) {
			message = message.replace(placeHolder.getPlaceHolder(), placeHolder.getReplace());
		}
		return ChatColor.stripColor(message);
	}

	public static String capitalizeFirstLetter(String original) {
		if (original == null || original.length() == 0) {
			return original;
		}
		return original.substring(0, 1).toUpperCase() + original.substring(1);
	}

	public static String getFormattedTime(long timeInMs) {
		String result = "";
		long timeInS = timeInMs / 1000;

		long hrs = timeInS / (60 * 60);
		timeInS -= hrs * (60 * 60);
		if (hrs > 0)
			result += (hrs + "h ");

		long mins = timeInS / 60;
		timeInS -= mins * 60;
		if (mins > 0)
			result += (mins + "m ");

		long secs = timeInS % 60;
		if (result.isEmpty() || secs > 0)
			result += (secs + "s ");

		return result.trim();
	}

	public static String getFormattedMoney(long money) {
		//QUADTRILLION
		DecimalFormat format = new DecimalFormat("##.00");

		if(money/1000000000000000L > 0) {
			return ("$" + format.format(money/1000000000000000.0) + "Q");
		}
		//TRILLION
		if(money/1000000000000L > 0) {
			return ("$" + format.format(money/1000000000000.0) + "T");
		}
		//BILLION
		if(money/1000000000L > 0) {
			return ("$" + format.format(money/1000000000.0) + "B");
		}
		//MILLION
		if(money/1000000L > 0) {
			return ("$" + format.format(money/1000000.0) + "M");
		}
		//THOUSANDS
		if(money/1000L > 0) {
			return ("$" + format.format(money/1000.0) + "K");
		}
		return ("$" + money);
	}

	public static String getProgressBar(long current, long max, int totalBars, String symbol) {
		float percent = (float) current / max;
		int progressBars = (int) (totalBars * percent);

		return Strings.repeat("" + ChatColor.GREEN + symbol, progressBars)
				+ Strings.repeat("" + ChatColor.GRAY + symbol, totalBars - progressBars);
	}

	public static boolean isCooldownDone(long start, long end, int seconds) {
		return (end - start) / 1000 >= seconds;
	}

}
