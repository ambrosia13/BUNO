package com.ambrosia.buno.card.impl.card;

import com.ambrosia.buno.Game;
import com.ambrosia.buno.card.api.Card;
import com.ambrosia.buno.card.api.Cards;
import com.ambrosia.buno.card.api.ColoredCard;
import com.ambrosia.buno.util.ColorUtils;

public class RegularCard implements ColoredCard {
	private final int color;
	private final int number;

	public RegularCard(int color, int number) {
		this.color = color;
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}

	public String getNumberAsString() {
		return switch(this.number) {
			case 0 -> "Zero";
			case 1 -> "One";
			case 2 -> "Two";
			case 3 -> "Three";
			case 4 -> "Four";
			case 5 -> "Five";
			case 6 -> "Six";
			case 7 -> "Seven";
			case 8 -> "Eight";
			case 9 -> "Nine";
			default -> throw new IllegalStateException("Unexpected value: " + this.number);
		};
	}
	
	@Override
	public int getColor() {
		return this.color;
	}

	@Override
	public boolean isValid(Card bottom) {
		boolean valid = this.color == Game.getInstance().currentColor;

		if(bottom instanceof RegularCard c) {
			valid |= this.number == c.getNumber();
		}

		return valid;
	}

	@Override
	public String toString() {
		return ColorUtils.colorize(getNumberAsString(), Cards.getColorDisplay(this.color));
	}
}
