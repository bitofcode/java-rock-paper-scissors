package com.bitofcode.rockpapersscissors;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.bitofcode.rockpapersscissors.Game.ShapeType;

public class RockPaperScissorsControllerTest {

	private RockPaperScissorsController controller;

	@Before
	public void setUp() {
		controller = new RockPaperScissorsController(new Game());
	}

	@Test
	public void return_game_result() {
		GameResult gameResult = controller.play(ShapeType.PAPER);

		assertNotNull(gameResult);
	}

}
