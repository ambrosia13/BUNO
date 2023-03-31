package com.ambrosia.buno.card.impl.card.action;

import com.ambrosia.buno.card.api.ActionCard;
import com.ambrosia.buno.card.api.ColoredCard;

public abstract class ColoredActionCard implements ColoredCard, ActionCard {
	protected final int color;

	protected ColoredActionCard(int color) {
		this.color = color;
	}

	@Override
	public int getColor() {
		return this.color;
	}
}
