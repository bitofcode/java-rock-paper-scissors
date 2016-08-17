package com.bitofcode.rockpapersscissors.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitofcode.rockpapersscissors.game.GameImpl;
import com.bitofcode.rockpapersscissors.game.ShapeType;

public class RockPaperScissorsControllerTest {

	private RockPaperScissorsController controller;

	@Before
	public void setUp() {
		controller = new RockPaperScissorsController(new GameImpl());
	}

	@Test
	public void return_game_result() {
		ResponseEntity<?> gameResult = controller.play(new GameInput(ShapeType.PAPER.name()));

		assertThat(gameResult.getStatusCode(), is(HttpStatus.OK));
		assertNotNull(gameResult.getBody());
		assertThat(gameResult.getBody().getClass(), is(equalTo(GameResult.class)));
	}

	@Test
	public void given_invalid_shape_return_http_status_bad_request() throws Exception {
		ResponseEntity<?> result = controller.play(new GameInput("Hello World!"));

		assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
		assertThat(result.getBody().getClass(), is(equalTo(String.class)));
	}

}