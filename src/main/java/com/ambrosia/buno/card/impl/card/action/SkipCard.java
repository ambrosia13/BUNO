package com.ambrosia.buno.card.impl.card.action;

import com.ambrosia.buno.Game;
import com.ambrosia.buno.card.api.Card;
import com.ambrosia.buno.card.api.Cards;
import com.ambrosia.buno.player.Player;
import com.ambrosia.buno.player.Players;
import com.ambrosia.buno.util.ColorUtils;

public class SkipCard extends ColoredActionCard {
	public SkipCard(int color) {
		super(color);
	}
	
	@Override
	public void onPlacedOnPile() {
		Player nextPlayer = Players.getInstance().getNextPlayer();
		System.out.println(nextPlayer.name + " has been skipped!");
		
		Game.getInstance().incrementRound();
	}
	
	@Override
	public boolean isValid(Card bottom) {
		return bottom instanceof SkipCard || this.getColor() == Game.getInstance().currentColor;
	}
	
	@Override
	public String toString() {
		return ColorUtils.colorize("Skip", Cards.getBoldColorDisplay(this.color));
	}
}
