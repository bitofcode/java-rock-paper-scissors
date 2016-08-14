package com.bitofcode.rockpapersscissors;

import com.bitofcode.rockpapersscissors.Game.WinnerType;
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
