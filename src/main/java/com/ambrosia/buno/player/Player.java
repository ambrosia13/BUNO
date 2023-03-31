package com.ambrosia.buno.player;

import com.ambrosia.buno.Game;
import com.ambrosia.buno.card.api.Card;
import com.ambrosia.buno.card.game.CardDeck;
import com.ambrosia.buno.card.game.CardPile;

import java.util.ArrayList;

public class Player {
	public final String name;
	private final ArrayList<Card> cards;
	
	/**
	 * Constructs a new Player with the given name and distributes seven cards to their deck.
	 * 
	 * @param name the name of the player
	 */
	public Player(String name) {
		this.name = name;
		
		this.cards = new ArrayList<>();
		takeCardsFromDeck(7);
	}
	
	public Player() {
		this("Player" + Game.getInstance().random.nextInt(1000));
	}
	
	/**
	 * Takes the specified number of cards from the card deck.
	 * 
	 * @param n how many cards to take
	 */
	public void takeCardsFromDeck(int n) {
		for(int i = 0; i < n; i++) {
			this.addCard(CardDeck.getInstance().takeCard());
		}
	}
	
	/**
	 * Removes the card at the specified index from the player's cards
	 * and adds it to the card pile
	 * 
	 * @param index the index at which to remove the card from the player's cards
	 */
	public void addCardToPile(int index) {
		CardPile.getInstance().addCard(this.removeCard(index));
	}
	
	
	public Card getCard(int index) {
		return this.cards.get(index);
	}
	public void setCard(int index, Card card) {
		this.cards.set(index, card);
	}
	public void addCard(Card card) {
		this.cards.add(card);
	}
	public Card removeCard(int index) {
		return this.cards.remove(index);
	}
	
	/**
	 * Returns the list of the player's cards. Should generally only be used for iterating;
	 * most other use cases are covered in other methods.
	 * 
	 * @return the list of the player's cards
	 */
	public ArrayList<Card> getCards() {
		return this.cards;
	}
	
	@Override
	public String toString() {
		return this.name + ": " + this.cards.toString();
	}
}
