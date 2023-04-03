package com.ambrosia.buno.card.impl.card.wild;

import com.ambrosia.buno.Game;
import com.ambrosia.buno.card.api.Card;
import com.ambrosia.buno.player.Player;
import com.ambrosia.buno.player.Players;
import com.ambrosia.buno.util.ColorUtils;

public class WildDrawFourCard extends WildCard {
	@Override
	public void onPlacedOnPile() {
		super.onPlacedOnPile();
		
		Game.getInstance().numCardsDrawnFromActionCards += 4;
		
		Player nextPlayer = Players.getInstance().getNextPlayer();
		
		nextPlayer.takeCardsFromDeck(4);
		System.out.println(nextPlayer.name + " has taken four random cards from the deck.");
	}
	
	/**
	 * According to the official Uno specification,
	 * wild draw four cards can only be played if the
	 * player can play no other cards.
	 */
	@Override
	public boolean isValid(Card bottom) {
		boolean isValid = true;
		
		for(Card card : Players.getInstance().getCurrentPlayer().getCards()) {
			if(!(card instanceof WildDrawFourCard)) {
				isValid &= card.isValid(bottom);
			}
		}
		
		return !isValid;
	}
	
	@Override
	public void onInvalidPlacement() {
		System.out.println("You can only play a Wild Draw Four Card if you have no other cards to play.");
	}
	
	@Override
	public String toString() {
		return ColorUtils.colorizeRainbow("Wild Card (Draw Four)");
	}
}
