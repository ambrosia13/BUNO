package com.ambrosia.buno;

public class Main {
	public static void main(String[] args) {
		// gradle doesn't play nice with exceptions apparently, so i'm using this
		// as a workaround.
		try {
			Game.run();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}