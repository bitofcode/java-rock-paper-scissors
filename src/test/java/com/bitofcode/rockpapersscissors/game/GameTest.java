package com.bitofcode.rockpapersscissors.game;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.bitofcode.rockpapersscissors.game.ShapeInvalidException;
import com.bitofcode.rockpapersscissors.game.Game;
import com.bitofcode.rockpapersscissors.game.GameImpl;
import com.bitofcode.rockpapersscissors.game.ShapeType;
import com.bitofcode.rockpapersscissors.game.WinnerType;

public class GameTest {

	private Game game;
	private Game gameToTestDidIWin;

	@Before
	public void canCreateGame() {
		game = new GameImpl();

		gameToTestDidIWin = generateGameWithPaperAsRandomShape();
	}

	private GameImpl generateGameWithPaperAsRandomShape() {
		return new GameImpl() {
			public ShapeType generateRandomShape() {
				return ShapeType.PAPER;
			}
		};
	}

	@Test(expected = ShapeInvalidException.class)
	public void given_null_then_throw_ArgumentInvalidException() throws Exception {
		game.play(null);
	}

	@Test
	public void given_shape_against_random_should_win() throws Exception {
		WinnerType didIWin = gameToTestDidIWin.play(ShapeType.SCISSOR);
		assertThat(didIWin, is(WinnerType.FIRST_WIN));
	}

	@Test
	public void given_shape_against_random_should_loose() throws Exception {
		WinnerType didIWin = gameToTestDidIWin.play(ShapeType.ROCK);
		assertThat(didIWin, is(WinnerType.SECOND_WIN));
	}

	@Test(expected = ShapeInvalidException.class)
	public void given_null_for_firstPlayer_then_throw_ArgumentInvalidException() throws Exception {
		game.play(null, ShapeType.PAPER);
	}

	@Test(expected = ShapeInvalidException.class)
	public void given_null_for_secondPlayer_then_throw_ArgumentInvalidException() throws Exception {
		game.play(ShapeType.PAPER, null);
	}

	@Test
	public void given_both_rock_no_one_wins() throws Exception {
		playAndAssert(ShapeType.ROCK, ShapeType.ROCK, WinnerType.NO_WINNER);
	}

	private void playAndAssert(ShapeType first, ShapeType second, WinnerType expectedWinner) {
		WinnerType winner = game.play(first, second);

		assertThat(winner, is(expectedWinner));
	}

	@Test
	public void given_both_paper_no_one_wins() throws Exception {
		playAndAssert(ShapeType.PAPER, ShapeType.PAPER, WinnerType.NO_WINNER);
	}

	@Test
	public void given_both_scissors_no_one_wins() throws Exception {
		playAndAssert(ShapeType.SCISSOR, ShapeType.SCISSOR, WinnerType.NO_WINNER);
	}

	@Test
	public void first_scissor_second_papaer_first_wins() throws Exception {
		playAndAssert(ShapeType.SCISSOR, ShapeType.PAPER, WinnerType.FIRST_WIN);
	}

	@Test
	public void first_paper_second_scissor_second_wins() throws Exception {
		playAndAssert(ShapeType.PAPER, ShapeType.SCISSOR, WinnerType.SECOND_WIN);
	}

	@Test
	public void first_paper_second_rock_first_wins() throws Exception {
		playAndAssert(ShapeType.PAPER, ShapeType.ROCK, WinnerType.FIRST_WIN);
	}

	@Test
	public void first_rock_second_paper_second_wins() throws Exception {
		playAndAssert(ShapeType.ROCK, ShapeType.PAPER, WinnerType.SECOND_WIN);
	}

	@Test
	public void first_rock_second_scissor_first_wins() throws Exception {
		playAndAssert(ShapeType.ROCK, ShapeType.SCISSOR, WinnerType.FIRST_WIN);
	}

	@Test
	public void first_scissor_second_rock_second_wins() throws Exception {
		playAndAssert(ShapeType.SCISSOR, ShapeType.ROCK, WinnerType.SECOND_WIN);
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
