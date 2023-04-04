package com.ambrosia.buno.util;

import com.ambrosia.buno.Game;

import java.util.ArrayList;
import java.util.function.IntConsumer;

public class OptionMap {
	public ArrayList<String> options;
	public IntConsumer onPicked;
	
	public String errorMessage;
	
	public OptionMap() {
		options = new ArrayList<>();
		onPicked = null;
		this.errorMessage = "Illegal input, please try again.";
	}
	
	public OptionMap add(String key) {
		options.add(key);
		return this;
	}
	
	public OptionMap onPicked(IntConsumer onPicked) {
		this.onPicked = onPicked;
		return this;
	}
	
	/**
	 * Displays this OptionMap to the user. The index displayed to the user is shifted
	 * up by 1, so it starts with 1 to be user-friendly.
	 */
	public OptionMap display() {
		StringBuilder toDisplay = new StringBuilder();
		
		for(int i = 0; i < options.size(); i++) {
			toDisplay.append('[').append(i + 1).append("] ").append(options.get(i)).append("\n");
		}
		
		System.out.println(toDisplay);
		return this;
	}
	
	/**
	 * Returns the index of the user's answer starting with zero.
	 * 
	 * @return the index of the user's answer
	 */
	public int getAnswer(Game game) {
		while(true) {
			String input = game.input();
			
			try {
				int inputAsInt = Integer.parseInt(input) - 1;
				
				if(inputAsInt >= 0 && inputAsInt <= options.size()) {
					return inputAsInt;
				} else {
					System.out.println(this.errorMessage);
				}
			} catch(NumberFormatException e) {
				for(String entry : options) {
					if(entry.toLowerCase().contains(input.toLowerCase())) {
						return options.indexOf(entry);
					}
				}
				
				System.out.println(this.errorMessage);
			}
		}
	}
	
	public int getAnswer() {
		return getAnswer(Game.getInstance());
	}
	
	/**
	 * Uses the IntConsumer to evaluate the user's answer. Could be used to 
	 * avoid verbosity.
	 */
	public void evaluate() {
		this.onPicked.accept(getAnswer());
	}
}
