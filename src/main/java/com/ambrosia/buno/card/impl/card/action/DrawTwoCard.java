package com.ambrosia.buno.card.impl.card.action;

import com.ambrosia.buno.Game;
import com.ambrosia.buno.card.api.Card;
import com.ambrosia.buno.card.api.Cards;
import com.ambrosia.buno.player.Player;
import com.ambrosia.buno.player.Players;
import com.ambrosia.buno.util.ColorUtils;

public class DrawTwoCard extends ColoredActionCard {
	public DrawTwoCard(int color) {
		super(color);
	}

	@Override
	public void onPlacedOnPile() {
		Game.getInstance().numCardsDrawnFromActionCards += 2;
		
		Player nextPlayer = Players.getInstance().getNextPlayer();
		
		nextPlayer.takeCardsFromDeck(2);
		System.out.println(nextPlayer.name + " has drawn two random cards.");
	}

	@Override
	public boolean isValid(Card bottom) {
		return bottom instanceof DrawTwoCard || this.getColor() == Game.getInstance().currentColor;
	}

	@Override
	public String toString() {
		return ColorUtils.colorize("Draw Two", Cards.getBoldColorDisplay(this.color));
	}
}
