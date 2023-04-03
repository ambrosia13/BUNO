package com.ambrosia.buno.card.impl.card.action;

import com.ambrosia.buno.Game;
import com.ambrosia.buno.card.api.Card;
import com.ambrosia.buno.card.api.Cards;
import com.ambrosia.buno.util.ColorUtils;

public class ReverseCard extends ColoredActionCard {
	public ReverseCard(int color) {
		super(color);
	}

	@Override
	public void onPlacedOnPile() {
		Game.getInstance().moveForward = !Game.getInstance().moveForward;
		System.out.println("Order has been reversed!");
	}

	@Override
	public boolean isValid(Card bottom) {
		return bottom instanceof ReverseCard || this.getColor() == Game.getInstance().currentColor;
	}

	@Override
	public String toString() {
		return ColorUtils.colorize("Reverse", Cards.getBoldColorDisplay(this.color));
	}
}
