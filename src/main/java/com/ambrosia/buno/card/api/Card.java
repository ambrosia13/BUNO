package com.ambrosia.buno.card.api;

public interface Card {
	default void onInvalidPlacement() {
		System.out.println("You can't place that card there!");
	}

	boolean isValid(Card bottom);
}
