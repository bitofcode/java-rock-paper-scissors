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
			throw new ShapeInvalidException("Shape is null");
	}

	private boolean isTheFirstShapeTheWinner(ShapeType firstShape, ShapeType secondShape) {
		return (firstShape.equals(ShapeType.PAPER) && secondShape.equals(ShapeType.ROCK))
				|| (firstShape.equals(ShapeType.SCISSOR) && secondShape.equals(ShapeType.PAPER))
				|| (firstShape.equals(ShapeType.ROCK) && secondShape.equals(ShapeType.SCISSOR));
	}

	private boolean isNoWinner(ShapeType firstShape, ShapeType secondShape) {
		return firstShape.equals(secondShape);
	}

	private WinnerType getTheWinnerOf(ShapeType firstShape, ShapeType secondShape) {
		if (isNoWinner(firstShape, secondShape))
			return WinnerType.NO_WINNER;

		return isTheFirstShapeTheWinner(firstShape, secondShape) ? WinnerType.FIRST_WIN : WinnerType.SECOND_WIN;
	}

	public ShapeType generateRandomShape() {
		return possibleshapes[randomNumberGenerator.nextInt(possibleshapes.length)];
	}

	public WinnerType play(ShapeType shape) {
		checkTheShape(shape);
		return getTheWinnerOf(shape, generateRandomShape());
	}

	public WinnerType play(ShapeType firstShape, ShapeType secondShape) {
		checkInputShapes(firstShape, secondShape);
		return getTheWinnerOf(firstShape, secondShape);
	}
}
