package com.ambrosia.buno.card.api;

import com.ambrosia.buno.util.ColorUtils;

/**
 * Utility methods and constants for dealing with cards.
 */
public class Cards {
	/**
	 * ID for the color yellow.
	 */
	public static final int COLOR_YELLOW = 1;
	
	/**
	 * ID for the color green.
	 */
	public static final int COLOR_GREEN = 2;
	
	/**
	 * ID for the color blue.
	 */
	public static final int COLOR_BLUE = 3;
	
	/**
	 * ID for the color red.
	 */
	public static final int COLOR_RED = 4;
	
	/**
	 * When given the color ID, returns the appropriate color escape code.
	 * 
	 * @param color the ID of the color
	 * @return the escape code representing the color
	 */
	public static String getColorDisplay(int color) {
		return switch(color) {
			case COLOR_YELLOW -> ColorUtils.DISPLAY_YELLOW;
			case COLOR_GREEN -> ColorUtils.DISPLAY_GREEN;
			case COLOR_BLUE -> ColorUtils.DISPLAY_BLUE;
			case COLOR_RED -> ColorUtils.DISPLAY_RED;
			default -> "";
		};
	}
	
	/**
	 * Same as Cards.getColorDisplay() but returns the escape code for
	 * bold colored text.
	 * 
	 * @see Cards#getColorDisplay(int) 
	 * @param color the ID of the color
	 * @return the escape code for the color
	 */
	public static String getBoldColorDisplay(int color) {
		return switch(color) {
			case COLOR_YELLOW -> ColorUtils.DISPLAY_YELLOW_BOLD;
			case COLOR_GREEN -> ColorUtils.DISPLAY_GREEN_BOLD;
			case COLOR_BLUE -> ColorUtils.DISPLAY_BLUE_BOLD;
			case COLOR_RED -> ColorUtils.DISPLAY_RED_BOLD;
			default -> "";
		};
	}
}
