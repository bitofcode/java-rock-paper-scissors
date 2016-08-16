package com.bitofcode.rockpapersscissors.game;

public class ShapeInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ShapeInvalidException() {
	}

	public ShapeInvalidException(String message) {
		super(message);
	}
}