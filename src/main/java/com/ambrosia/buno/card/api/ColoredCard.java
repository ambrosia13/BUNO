package com.ambrosia.buno.card.api;

public interface ColoredCard extends Card {
	/**
	 * Returns the color ID for this card's color.
	 * 
	 * @return the color ID for the card color
	 * @see Cards#COLOR_YELLOW
	 * @see Cards#COLOR_GREEN
	 * @see Cards#COLOR_BLUE
	 * @see Cards#COLOR_RED
	 */
	int getColor();
}
