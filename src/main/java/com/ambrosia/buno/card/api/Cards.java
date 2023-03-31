package com.ambrosia.buno.card.api;

import com.ambrosia.buno.util.ColorUtils;

/**
 * Utility methods and constants for dealing with cards.
 */
public class Cards {
	public static final int COLOR_YELLOW = 1;
	public static final int COLOR_GREEN = 2;
	public static final int COLOR_BLUE = 3;
	public static final int COLOR_RED = 4;
	
	public static String getColorDisplay(int color) {
		return switch(color) {
			case COLOR_YELLOW -> ColorUtils.DISPLAY_YELLOW;
			case COLOR_GREEN -> ColorUtils.DISPLAY_GREEN;
			case COLOR_BLUE -> ColorUtils.DISPLAY_BLUE;
			case COLOR_RED -> ColorUtils.DISPLAY_RED;
			default -> null;
		};
	}
}
