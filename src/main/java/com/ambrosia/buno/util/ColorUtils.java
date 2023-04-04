package com.ambrosia.buno.util;

/**
 * Class that contains utilities for dealing with console colors.
 */
public final class ColorUtils {
	private ColorUtils() {
		// prevent instantiation
	}

	public static final String COLOR_RESET = "\033[0m";

	public static final String DISPLAY_YELLOW = "\033[0;33m";
	public static final String DISPLAY_GREEN = "\033[0;32m";
	public static final String DISPLAY_BLUE = "\033[0;34m";
	public static final String DISPLAY_RED = "\033[0;31m";
	
	public static final String DISPLAY_YELLOW_BOLD = "\033[1;33m";
	public static final String DISPLAY_GREEN_BOLD = "\033[1;32m";
	public static final String DISPLAY_BLUE_BOLD = "\033[1;34m";
	public static final String DISPLAY_RED_BOLD = "\033[1;31m";

	public static final String DISPLAY_PURPLE = "\033[0;35m";
	public static final String DISPLAY_CYAN = "\033[0;36m";
	public static final String DISPLAY_CYAN_BRIGHT = "\033[0;96m";

	private static final String[] colors = new String[] {
		DISPLAY_RED, DISPLAY_YELLOW, DISPLAY_GREEN,
		DISPLAY_CYAN, DISPLAY_BLUE, DISPLAY_PURPLE
	};
	
	/**
	 * Colorizes the given string to the given color.
	 * 
	 * @param src the string whose color to modify
	 * @param color the color escape code to use
	 * @return the colored string
	 */
	public static String colorize(String src, String color) {
		return color + src + COLOR_RESET;
	}
	
	/**
	 * Same as the colorize() method, but colors as a rainbow.
	 * 
	 * @param src the string whose color to modify
	 * @return the colored string
	 */
	public static String colorizeRainbow(String src) {
		int startingIndex = (int) (Math.random() * colors.length);

		char[] chars = src.toCharArray();
		StringBuilder coloredSrc = new StringBuilder();

		for(int i = 0; i < chars.length; i++) {
			coloredSrc.append(colorize(String.valueOf(chars[i]), colors[(i + startingIndex) % colors.length]));
		}

		return coloredSrc.toString();
	}

	public static final String YELLOW = colorize("Yellow", DISPLAY_YELLOW);
	public static final String GREEN = colorize("Green", DISPLAY_GREEN);
	public static final String BLUE = colorize("Blue", DISPLAY_BLUE);
	public static final String RED = colorize("Red", DISPLAY_RED);
	
	/**
	 * @param code the escape code that represents the color
	 * @return the literal word of the color
	 */
	public static String getColorString(String code) {
		return switch(code) {
			case DISPLAY_YELLOW -> YELLOW;
			case DISPLAY_GREEN -> GREEN;
			case DISPLAY_BLUE -> BLUE;
			case DISPLAY_RED -> RED;
			default -> throw new IllegalArgumentException("Unexpected value: " + code);
		};
	}
}
