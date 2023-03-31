package com.ambrosia.buno.card.game;

import com.ambrosia.buno.Game;
import com.ambrosia.buno.card.api.Card;
import com.ambrosia.buno.card.api.Cards;
import com.ambrosia.buno.card.api.ColoredCard;
import com.ambrosia.buno.card.impl.card.RegularCard;
import com.ambrosia.buno.card.impl.card.action.DrawTwoCard;
import com.ambrosia.buno.card.impl.card.action.ReverseCard;
import com.ambrosia.buno.card.impl.card.action.SkipCard;
import com.ambrosia.buno.card.impl.card.wild.WildCard;
import com.ambrosia.buno.card.impl.card.wild.WildDrawFourCard;

import java.util.ArrayList;

public class CardDeck {
	private static CardDeck instance;

	private final ArrayList<Card> cards;

	private CardDeck() {
		cards = new ArrayList<>();
		expandDeck();
	}
	
	public static synchronized CardDeck getInstance() {
		if(instance == null) {
			instance = new CardDeck();
		}
		
		return instance;
	}
	
	/**
	 * Expands the deck, adding the standard dealing to the deck. Can be called
	 * multiple times when, for example, the deck has run out of cards.
	 */
	public void expandDeck() {
		dealColoredCards(Cards.COLOR_YELLOW);
		dealColoredCards(Cards.COLOR_GREEN);
		dealColoredCards(Cards.COLOR_BLUE);
		dealColoredCards(Cards.COLOR_RED);
		
		addCard(new WildCard(), 4);
		addCard(new WildDrawFourCard(), 4);
	}
	
	private void dealColoredCards(int color) {
		addCard(new RegularCard(color, 0));
		
		for(int i = 1; i <= 9; i++) {
			addCard(new RegularCard(color, i), 2);
		}
		
		addCard(new DrawTwoCard(color), 2);
		addCard(new ReverseCard(color), 2);
		addCard(new SkipCard(color), 2);
	}
	
	/**
	 * Adds a card to the deck once.
	 * 
	 * @param card the card to add
	 */
	public void addCard(Card card) {
		cards.add(card);
	}
	
	/**
	 * Adds a card to the deck an arbitrary number of times.
	 * 
	 * @param card the card to add
	 * @param n how many times to add it
	 */
	public void addCard(Card card, int n) {
		// ensures that only one card has to be created in memory,
		// but can be added to the deck multiple times
		for(int i = 0; i < n; i++) {
			addCard(card);
		}
	}
	public Card takeCard() {
		return cards.remove(Game.getInstance().random.nextInt(cards.size()));
	}
}
