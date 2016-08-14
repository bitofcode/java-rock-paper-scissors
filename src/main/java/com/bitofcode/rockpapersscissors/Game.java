package com.bitofcode.rockpapersscissors;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class Game {

	private Random randomNumberGenerator = new Random(System.currentTimeMillis());
	private static ShapeType[] possibleshapes = { ShapeType.PAPER, ShapeType.ROCK, ShapeType.SCISSOR };

	public class ArgumentInvalidException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	}

	public enum ShapeType {
		ROCK, PAPER, SCISSOR
	}

	public enum WinnerType {
		FIRST_WIN, SECOND_WIN, NO_WINNER
	}

	public WinnerType doIWin(ShapeType shape) {
		if (shape == null)
			throw new ArgumentInvalidException();
		return play(shape, generateRandomShape());
	}

	public WinnerType play(ShapeType firstShape, ShapeType secondShape) {
		checkInputShapes(firstShape, secondShape);
		if (firstShape.equals(secondShape))
			return WinnerType.NO_WINNER;
		if (isFirstWinner(firstShape, secondShape))
			return WinnerType.FIRST_WIN;

		return WinnerType.SECOND_WIN;
	}

	private boolean isFirstWinner(ShapeType firstShape, ShapeType secondShape) {
		return (firstShape.equals(ShapeType.PAPER) && secondShape.equals(ShapeType.ROCK))
				|| (firstShape.equals(ShapeType.SCISSOR) && secondShape.equals(ShapeType.PAPER))
				|| (firstShape.equals(ShapeType.ROCK) && secondShape.equals(ShapeType.SCISSOR));
	}

	private void checkInputShapes(ShapeType firstShape, ShapeType secondShape) {
		if (firstShape == null)
			throw new ArgumentInvalidException();
		if (secondShape == null)
			throw new ArgumentInvalidException();
	}

	public ShapeType generateRandomShape() {
		return possibleshapes[randomNumberGenerator.nextInt(possibleshapes.length)];
	}

}
