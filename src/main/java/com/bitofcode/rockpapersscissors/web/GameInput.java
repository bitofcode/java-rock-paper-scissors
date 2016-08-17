package com.bitofcode.rockpapersscissors.web;

public class GameInput {
	private String guess;

	public GameInput(String guess) {
		this.guess = guess;
	}

	public GameInput() {
	}

	public String getGuess() {
		return guess;
	}

	public void setGuess(String guess) {
		this.guess = guess;
	}

}
