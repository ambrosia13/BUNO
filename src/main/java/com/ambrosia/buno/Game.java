package com.ambrosia.buno;

import com.ambrosia.buno.card.api.ActionCard;
import com.ambrosia.buno.card.api.Card;
import com.ambrosia.buno.card.api.Cards;
import com.ambrosia.buno.card.api.ColoredCard;
import com.ambrosia.buno.card.game.CardDeck;
import com.ambrosia.buno.card.game.CardPile;
import com.ambrosia.buno.card.impl.card.action.ReverseCard;
import com.ambrosia.buno.card.impl.card.action.SkipCard;
import com.ambrosia.buno.card.impl.card.wild.WildCard;
import com.ambrosia.buno.player.Player;
import com.ambrosia.buno.player.Players;
import com.ambrosia.buno.util.ColorUtils;
import com.ambrosia.buno.util.OptionMap;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	private static Game instance;

	// Game state variables
	public final Scanner in;
	public final Random random;


	private int currentRound;
	public int currentColor;
	public boolean moveForward;
	
	// Stats variables
	public int totalRounds;
	public int numWildCards;
	public int numCardsDrawnFromActionCards;
	
	private Game() {
		in = new Scanner(System.in);
		random = new Random();

		currentRound = 0;
		currentColor = -1;
		moveForward = true;
		
		totalRounds = 0;
		numWildCards = 0;
		numCardsDrawnFromActionCards = 0;
	}
	
	public int getCurrentRound() {
		return currentRound;
	}
	
	// Called at the end of each round
	public void incrementRound() {
		// clear console
		clearConsole();
		
		if(moveForward) currentRound++;
		else currentRound--;
		
		// If the most recent card played is a colored card, then the current color
		// changes to that card's color.
		Card topCard = CardPile.getInstance().getTopCard();
		if(topCard instanceof ColoredCard c) {
			currentColor = c.getColor();
		}
		
		// increment the total rounds no matter what
		totalRounds++;
	}
	public int getDirection() {
		if(moveForward) return 1;
		else return -1;
	}

	public static synchronized Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}

		return instance;
	}

	public static void run() {
		// Singleton hell
		final Game game = Game.getInstance();
		final CardDeck deck = CardDeck.getInstance();
		final CardPile pile = CardPile.getInstance();
		final Players players = Players.getInstance();
		
		// Title //
		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println("|                             Welcome to BUNO!                             |");
		System.out.println("|                               (Better Uno)                               |");
		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println();
		System.out.println();
		
		sleep(4000);
		
		System.out.println("Please enter the name of the next player you want to add, or 'done' to finish.");
		System.out.println("You can enter multiple names at once by separating them with spaces.");
		
		initPlayers: while(true) {
			String input = game.in.next();
			
			if(input.equalsIgnoreCase("done")) {
				if(players.getPlayerCount() < 2) {
					System.out.print("You need at least 2 players to play BUNO. ");
					System.out.printf("You currently have %s. %n", players.getPlayerCount());
					
					continue initPlayers;
				}
				
				System.out.println();
				break initPlayers;
			}
			
			for(Player player : players.getPlayers()) {
				if(player.name.equals(input)) {
					System.out.printf("There is another player with the name %s. ", input);
					System.out.println("Please enter in a unique name.");
					System.out.println();
					
					continue initPlayers;
				}
			}
			
			Player player;
			if(input.equals("")) {
				player = new Player();
			} else {
				player = new Player(input);
			}
			
			players.addPlayer(player);
			System.out.printf("Player %s added.%n", input);
			System.out.println();
		}
		
		System.out.println("Playing with the following players: ");
		players.getPlayers().forEach(n -> System.out.println("\t" + n.name));
		System.out.println();
		
		sleep(1000);
		
		System.out.println("Distributed 7 cards to each player. Start game?");
		
		new OptionMap()
			.add("Yes").add("No")
			.onPicked(n -> {
				if(n == 1) {
					System.out.println("Stopping!");
					System.exit(0);
				}
			})
			.display().evaluate();
		
		System.out.println("Starting!");
		sleep(4000);
		
		gameLoop: while(true) {
			Player currentPlayer = players.getCurrentPlayer(), 
			       previousPlayer = players.getPreviousPlayer();
			
			Card previousCard = pile.getTopCard();
			
			if(previousCard instanceof SkipCard) {
				previousPlayer = players.getPlayer(game.getCurrentRound() - game.getDirection() * 2);
			} else if(previousCard instanceof ReverseCard) {
				previousPlayer = players.getPlayer(game.getCurrentRound() + game.getDirection());
			}
			
			System.out.println();
			System.out.printf("It is now %s's turn. %n", ColorUtils.colorize(currentPlayer.name, ColorUtils.DISPLAY_CYAN_BRIGHT));
			if(game.totalRounds > 0) {
				System.out.printf("Last round, %s placed a %s. %n", previousPlayer.name, previousCard.toString());
				
				if(pile.getTopCard() instanceof WildCard) {
					System.out.printf("Since they placed a wild card, the color has been changed to %s. %n", ColorUtils.getColorString(Cards.getColorDisplay(game.currentColor)));
				}
			} else {
				System.out.printf("The top-most card in the card pile is %s. %n", pile.getTopCard());
			}
			
			System.out.println();
			System.out.println("When you are alone and no one can see your screen, please type anything in.");
			game.in.next();
			
			System.out.println();
			
			playCard: while(true) {
				System.out.println("Which card do you want to play?");
				
				OptionMap options = new OptionMap();
				options.add("None, draw card from deck");
				options.add("Show me only the cards I can play");
				
				for(Card card : currentPlayer.getCards()) {
					options.add(card.toString() + 
						(
							card.isValid(pile.getTopCard()) ? 
								" - " + ColorUtils.colorize("+", ColorUtils.DISPLAY_GREEN) :
								" - " + ColorUtils.colorize("x", ColorUtils.DISPLAY_RED)
						)
					);
				}
				
				options.display();
				int answer = options.getAnswer();
				
				if(answer == 0) { // they selected draw card from deck
					currentPlayer.takeCardsFromDeck(1);
					System.out.printf(
						"You took a %s from the deck. %n",
						currentPlayer.getCard(currentPlayer.getCards().size() - 1).toString()
					);
					
					sleep(500);
					continue playCard;
				} else if(answer == 1) { // display only the cards they can play
					System.out.println("Sure! Here are the cards that you can currently play:");
					
					OptionMap newOptions = new OptionMap();
					newOptions.add("Exit");
					
					ArrayList<Integer> indices = new ArrayList<>();
					
					for(int i = 0; i < currentPlayer.getCards().size(); i++) {
						Card card = currentPlayer.getCard(i);
						
						if(card.isValid(pile.getTopCard())) {
							newOptions.add(card.toString());
							indices.add(i);
						}
					}
					
					newOptions.display();
					int newAnswer = newOptions.getAnswer();
					
					if(newAnswer == 0) continue playCard;
					else newAnswer -= 1;
					
					// Select the index of the card in the real deck
					answer = indices.get(newAnswer) + 2;
				}
				
				// offset to 0-based index.
				answer -= 2;
				
				Card toPlay = currentPlayer.getCard(answer);
				
				if(!toPlay.isValid(pile.getTopCard())) {
					toPlay.onInvalidPlacement();
					
					sleep(500);
					continue playCard;
				}
				
				System.out.printf("Adding %s to pile!", toPlay);
				System.out.println();
				
				currentPlayer.addCardToPile(answer);
				
				if(toPlay instanceof ActionCard actionCard) {
					System.out.println(actionCard);
					actionCard.onPlacedOnPile();
					
					System.out.println();
				}
				
				break playCard;
			}
			
			// Check for win
			for(Player player : players.getPlayers()) {
				if(player.getCards().size() > 0) continue;
				
				System.out.printf(
					"""
					-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
					Congratulations, %s! You're the winner!
					-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
					Game stats:
					\tTotal rounds: %d
					\tWild Cards used: %d
					\tCards drawn from action cards: %d
											
					Hope you enjoyed!
					""",
					player.name,
					game.totalRounds, game.numWildCards, game.numCardsDrawnFromActionCards
				);
				
				System.exit(0);
			}
			
			sleep(4000);
			
			game.incrementRound();
		}
	}
	
	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch(InterruptedException ignored) {
			
		}
	}
	
	private static void clearConsole() {
		System.out.println(System.lineSeparator().repeat(150));
	}
}
