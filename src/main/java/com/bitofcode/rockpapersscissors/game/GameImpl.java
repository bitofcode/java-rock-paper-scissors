package com.bitofcode.rockpapersscissors.game;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class GameImpl implements Game {

	private static Random randomNumberGenerator = new Random(System.currentTimeMillis());
	private static ShapeType[] possibleshapes = { ShapeType.PAPER, ShapeType.ROCK, ShapeType.SCISSOR };

	private void checkInputShapes(ShapeType firstShape, ShapeType secondShape) {
		checkTheShape(firstShape);
		checkTheShape(secondShape);
	}

	private void checkTheShape(ShapeType shape) {
		if (shape == null)
			throw ShapeInvalidException.createShapeInvalidException("Shape is null.");
	}

	private boolean amITheWinner(ShapeType myGussedShape, ShapeType otherGussedShape) {
		return (myGussedShape.equals(ShapeType.PAPER) && otherGussedShape.equals(ShapeType.ROCK))
				|| (myGussedShape.equals(ShapeType.SCISSOR) && otherGussedShape.equals(ShapeType.PAPER))
				|| (myGussedShape.equals(ShapeType.ROCK) && otherGussedShape.equals(ShapeType.SCISSOR));
	}

	private boolean isNoWinner(ShapeType myGussedShape, ShapeType otherGuessedShape) {
		return myGussedShape.equals(otherGuessedShape);
	}

	private WinnerType getTheWinnerOf(ShapeType myGuessedShape, ShapeType otherGuessedShape) {
		if (isNoWinner(myGuessedShape, otherGuessedShape))
			return WinnerType.NO_WINNER;

		return amITheWinner(myGuessedShape, otherGuessedShape) ? WinnerType.I_WIN : WinnerType.THE_OTHER_WINS;
	}

	public ShapeType generateRandomShape() {
		return possibleshapes[randomNumberGenerator.nextInt(possibleshapes.length)];
	}

	public WinnerType play(ShapeType shape) {
		checkTheShape(shape);
		return getTheWinnerOf(shape, generateRandomShape());
	}

	public WinnerType play(ShapeType myGuessedShape, ShapeType otherGuessedShape) {
		checkInputShapes(myGuessedShape, otherGuessedShape);
		return getTheWinnerOf(myGuessedShape, otherGuessedShape);
	}
}
