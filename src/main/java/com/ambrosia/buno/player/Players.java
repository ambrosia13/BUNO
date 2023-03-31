package com.ambrosia.buno.player;

import com.ambrosia.buno.Game;

import java.util.ArrayList;

public class Players {
	private static Players instance;
	
	private final ArrayList<Player> players;
	
	private Players() {
		players = new ArrayList<>();
	}
	
	public static synchronized Players getInstance() {
		if(instance == null) {
			instance = new Players();
		}
		
		return instance;
	}
	
	/**
	 * Adds a player to the player list. Should only be called during game initialization.
	 * 
	 * @param player the player to add
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	/**
	 * Returns the player at the specified index. 
	 * 
	 * @param index the index at which to get the player. Note that this does not
	 *              need to be limited in size at all; because of the nature of 
	 *              reverse cards and other mechanics of BUNO, this will be wrapped
	 *              to the current number of players. Thus, you can pass in the
	 *              current round as a parameter.
	 * @return the player at the specified index
	 */
	public Player getPlayer(int index) {
		return players.get(mod(index, players.size()));
	}
	
	public Player getPreviousPlayer() {
		return getPlayer(Game.getInstance().getCurrentRound() + (Game.getInstance().moveForward ? -1 : 1));
	}
	public Player getCurrentPlayer() {
		return getPlayer(Game.getInstance().getCurrentRound());
	}
	public Player getNextPlayer() {
		return getPlayer(Game.getInstance().getCurrentRound() + (Game.getInstance().moveForward ? 1 : -1));
	}
	
	public int getPlayerCount() {
		return players.size();
	}
	
	/**
	 * Returns the list of players. Should only be used for iterating over all
	 * players; most other use cases are already covered.
	 * 
	 * @return ArrayList of players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	// modulo that doesn't flip at 0
	private static int mod(int a, int b) {
		return (a % b + b) % b;
	}
}
