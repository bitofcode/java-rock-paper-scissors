package com.bitofcode.rockpapersscissors.web;

import com.bitofcode.rockpapersscissors.game.WinnerType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameResult {

	private WinnerType result;

	@JsonCreator
	public GameResult(@JsonProperty("result") WinnerType result) {
		this.result = result;
	}

	public WinnerType getResult() {
		return result;
	}

	public void setResult(WinnerType result) {
		this.result = result;
	}

	public GameResult() {
	}

}
