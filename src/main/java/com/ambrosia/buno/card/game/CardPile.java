package com.ambrosia.buno.card.game;

import com.ambrosia.buno.Game;
import com.ambrosia.buno.card.api.ActionCard;
import com.ambrosia.buno.card.api.Card;
import com.ambrosia.buno.card.api.ColoredCard;
import com.ambrosia.buno.card.impl.card.RegularCard;

import java.util.ArrayList;

public class CardPile {
	private static CardPile instance;
	
	private final ArrayList<Card> cards;
	
	private CardPile() {
		cards = new ArrayList<>();
		takeRegularCardFromDeck();
		
		ColoredCard c = (ColoredCard) getTopCard();
		Game.getInstance().currentColor = c.getColor();
	}
	
	public static synchronized CardPile getInstance() {
		if(instance == null) {
			instance = new CardPile();
		}
		
		return instance;
	}
	
	/**
	 * Adds a card to the pile, taken from the deck.
	 */
	public void takeCardFromDeck() {
		this.cards.add(CardDeck.getInstance().takeCard());
	}
	
	/**
	 * Ensures that the card taken from the deck is not an action card.
	 */
	public void takeRegularCardFromDeck() {
		// NB: this would actually be slow and I am too lazy to make it fast,
		// so this will just make a new card that isn't an action card.
		
		this.cards.add(
			new RegularCard(
				Game.getInstance().random.nextInt(1, 5),
				Game.getInstance().random.nextInt(10)
			)
		);
	}
	
	/**
	 * Adds the specified card to the pile. When calling this method, if the card
	 * is being taken from a player, callers should ensure that the card is
	 * removed from the player's hand.
	 * 
	 * @param card the card to add to the pile
	 */
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	/**
	 * Fetches the top-most card in the pile; typically used to check if a card
	 * placement is valid.
	 * 
	 * @return the top-most card in the pile
	 */
	public Card getTopCard() {
		return this.cards.get(this.cards.size() - 1);
	}
}
