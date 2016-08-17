package com.bitofcode.rockpapersscissors.game;

public class ShapeInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ShapeInvalidException() {
	}

	public ShapeInvalidException(String message) {
		super(message);
	}

	public static ShapeInvalidException createShapeInvalidException(String message) {
		return new ShapeInvalidException(message + " Shape should be one of {ROCK, PAPER, SCISSOR}");
	}
}