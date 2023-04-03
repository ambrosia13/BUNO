package com.ambrosia.buno.card.api;

public interface ActionCard extends Card {
	/**
	 * Is called just after a card is placed on the card pile. Should only be used for
	 * action cards and may or may not affect the game state or the players.
	 */
	void onPlacedOnPile();
}
