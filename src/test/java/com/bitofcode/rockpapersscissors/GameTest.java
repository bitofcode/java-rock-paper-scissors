package com.bitofcode.rockpapersscissors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.bitofcode.rockpapersscissors.Game.ShapeType;
import com.bitofcode.rockpapersscissors.Game.WinnerType;

public class GameTest {

	private Game game;
	private Game gameToTestDidIWin;

	@Before
	public void canCreateGame() {
		game = new Game();
		gameToTestDidIWin = new Game() {
			@Override
			public ShapeType generateRandomShape() {
				return ShapeType.PAPER;
			}
		};
	}

	@Test(expected = Game.ArgumentInvalidException.class)
	public void given_null_then_throw_ArgumentInvalidException() throws Exception {
		game.doIWin(null);
	}

	@Test
	public void given_shape_against_random_should_win() throws Exception {
		WinnerType didIWin = gameToTestDidIWin.doIWin(ShapeType.SCISSOR);
		assertThat(didIWin, is(WinnerType.FIRST_WIN));
	}

	@Test
	public void given_shape_against_random_should_loose() throws Exception {
		WinnerType didIWin = gameToTestDidIWin.doIWin(ShapeType.ROCK);
		assertThat(didIWin, is(WinnerType.SECOND_WIN));
	}

	@Test(expected = Game.ArgumentInvalidException.class)
	public void given_null_for_firstPlayer_then_throw_ArgumentInvalidException() throws Exception {
		game.play(null, ShapeType.PAPER);
	}

	@Test(expected = Game.ArgumentInvalidException.class)
	public void given_null_for_secondPlayer_then_throw_ArgumentInvalidException() throws Exception {
		game.play(ShapeType.PAPER, null);
	}

	@Test
	public void given_both_rock_no_one_wins() throws Exception {
		playAndAssert(ShapeType.ROCK, ShapeType.ROCK, WinnerType.NO_WINNER);
	}

	private void playAndAssert(ShapeType first, ShapeType second, WinnerType expectedWinner) {
		Game.WinnerType winner = game.play(first, second);

		assertThat(winner, is(expectedWinner));
	}

	@Test
	public void given_both_paper_no_one_wins() throws Exception {
		playAndAssert(Game.ShapeType.PAPER, Game.ShapeType.PAPER, Game.WinnerType.NO_WINNER);
	}

	@Test
	public void given_both_scissors_no_one_wins() throws Exception {
		playAndAssert(Game.ShapeType.SCISSOR, Game.ShapeType.SCISSOR, WinnerType.NO_WINNER);
	}

	@Test
	public void first_scissor_second_papaer_first_wins() throws Exception {
		playAndAssert(Game.ShapeType.SCISSOR, Game.ShapeType.PAPER, Game.WinnerType.FIRST_WIN);
	}

	@Test
	public void first_paper_second_scissor_second_wins() throws Exception {
		playAndAssert(Game.ShapeType.PAPER, Game.ShapeType.SCISSOR, Game.WinnerType.SECOND_WIN);
	}

	@Test
	public void first_paper_second_rock_first_wins() throws Exception {
		playAndAssert(Game.ShapeType.PAPER, Game.ShapeType.ROCK, Game.WinnerType.FIRST_WIN);
	}

	@Test
	public void first_rock_second_paper_second_wins() throws Exception {
		playAndAssert(Game.ShapeType.ROCK, Game.ShapeType.PAPER, Game.WinnerType.SECOND_WIN);
	}

	@Test
	public void first_rock_second_scissor_first_wins() throws Exception {
		playAndAssert(Game.ShapeType.ROCK, Game.ShapeType.SCISSOR, Game.WinnerType.FIRST_WIN);
	}

	@Test
	public void first_scissor_second_rock_second_wins() throws Exception {
		playAndAssert(Game.ShapeType.SCISSOR, Game.ShapeType.ROCK, Game.WinnerType.SECOND_WIN);
	}

	@Test
	public void generate_shape_doesnt_return_null() throws Exception {
		ShapeType generatedShape = game.generateRandomShape();
		assertNotNull(generatedShape);
	}

	@Test
	public void generate_different_shapes_after_100_random_generated_shapes() throws Exception {
		Set<ShapeType> generatedShapes = new HashSet<ShapeType>();
		for (int i = 0; i < 100 && generatedShapes.size() < 3; i++)
			generatedShapes.add(game.generateRandomShape());

		assertThat(generatedShapes.size(), is(3));
	}
}
