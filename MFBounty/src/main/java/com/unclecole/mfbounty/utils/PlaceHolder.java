package com.unclecole.mfbounty.utils;

public class PlaceHolder {
	
	private final String placeHolder;
	private final String replace;
	
	public PlaceHolder(String placeHolder, String replace) {
		this.placeHolder = placeHolder;
		this.replace = replace;
	}
	
	public PlaceHolder(String placeHolder, boolean bool) {
		this.replace = String.valueOf(bool);
		this.placeHolder = placeHolder;
	}
	
	public PlaceHolder(String placeHolder, int i) {
		this.replace = String.valueOf(i);
		this.placeHolder = placeHolder;
	}

	public PlaceHolder(String placeHolder, double i) {
		this.replace = String.valueOf(i);
		this.placeHolder = placeHolder;
	}

	public PlaceHolder(String placeHolder, long i) {
		this.replace = String.valueOf(i);
		this.placeHolder = placeHolder;
	}

	public PlaceHolder(String placeHolder, float i) {
		this.replace = String.valueOf(i);
		this.placeHolder = placeHolder;
	}

	public String getPlaceHolder() {
		return placeHolder;
	}

	public String getReplace() {
		return replace;
	}
}
