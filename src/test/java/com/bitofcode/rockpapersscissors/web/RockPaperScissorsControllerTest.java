package com.bitofcode.rockpapersscissors.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bitofcode.rockpapersscissors.game.GameImpl;
import com.bitofcode.rockpapersscissors.game.ShapeInvalidException;
import com.bitofcode.rockpapersscissors.game.ShapeType;
import com.bitofcode.rockpapersscissors.web.GameResult;
import com.bitofcode.rockpapersscissors.web.RockPaperScissorsController;

public class RockPaperScissorsControllerTest {

	private RockPaperScissorsController controller;

	@Before
	public void setUp() {
		controller = new RockPaperScissorsController(new GameImpl());
	}

	@Test
	public void return_game_result() {
		GameResult gameResult = controller.play(ShapeType.PAPER);
		assertNotNull(gameResult);
	}
	
	@Test(expected = ShapeInvalidException.class)
	public void given_null_shape_then_throw_exception() throws Exception {
		controller.play(null);
	}

}
