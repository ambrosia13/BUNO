package com.ambrosia.buno.card.api;

public interface Card {
	/**
	 * Is called whenever a card placement is found to be invalid. 
	 * Is expected to print something to the console.
	 */
	default void onInvalidPlacement() {
		System.out.println("You can't place that card there!");
	}
	
	/**
	 * Whether or not this card is valid when placed on top of the given
	 * bottom card.
	 *
	 * @param bottom the card against which to compare this card's validity
	 * @return whether the caller object is valid against the bottom card
	 */
	boolean isValid(Card bottom);
}
