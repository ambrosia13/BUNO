package com.ambrosia.buno.card.impl.card.action;

import com.ambrosia.buno.card.api.ActionCard;
import com.ambrosia.buno.card.api.ColoredCard;

/**
 * Abstract class that implements the two interfaces ColoredCard and
 * ActionCard. Cards that have both an action and a color should
 * inherit from this class.
 */
public abstract class AbstractColoredActionCard implements ColoredCard, ActionCard {
	protected final int color;

	protected AbstractColoredActionCard(int color) {
		this.color = color;
	}

	@Override
	public int getColor() {
		return this.color;
	}
}
