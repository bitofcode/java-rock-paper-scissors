package com.bitofcode.rockpapersscissors.game;

public interface Game {

	WinnerType play(ShapeType shape);

	WinnerType play(ShapeType firstShape, ShapeType secondShape);
	
	ShapeType generateRandomShape();

}