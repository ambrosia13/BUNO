package com.ambrosia.buno.card.impl.card.wild;

import com.ambrosia.buno.Game;
import com.ambrosia.buno.card.api.ActionCard;
import com.ambrosia.buno.card.api.Card;
import com.ambrosia.buno.card.api.Cards;
import com.ambrosia.buno.util.ColorUtils;
import com.ambrosia.buno.util.OptionMap;

public class WildCard implements ActionCard {
	@Override
	public void onPlacedOnPile() {
		Game.getInstance().numWildCards++;
		
		System.out.println("What color do you want to change to?");
		
		new OptionMap()
			.add(ColorUtils.YELLOW).add(ColorUtils.GREEN)
			.add(ColorUtils.BLUE).add(ColorUtils.RED)
			.onPicked(n -> {
				// Color ID for the new color
				int targetColor = n + 1;
				
				System.out.printf(
					"The color was changed from %s to %s. %n",
					ColorUtils.getColorString(Cards.getColorDisplay(Game.getInstance().currentColor)),
					ColorUtils.getColorString(Cards.getColorDisplay(targetColor))
				);
				
				Game.getInstance().currentColor = targetColor;
			})
			.display()
			.evaluate();
	}
	
	@Override
	public boolean isValid(Card bottom) {
		return true;
	}
	
	@Override
	public String toString() {
		return ColorUtils.colorizeRainbow("Wild Card");
	}
}
