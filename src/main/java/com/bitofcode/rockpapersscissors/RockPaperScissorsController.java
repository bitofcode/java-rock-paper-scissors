package com.bitofcode.rockpapersscissors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bitofcode.rockpapersscissors.Game.ShapeType;

@RestController
@RequestMapping(path = { "/api/rock-paper-scissors" }, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
public class RockPaperScissorsController {

	private Game game;

	@Autowired
	public RockPaperScissorsController(Game game) {
		this.game = game;
	}

	@RequestMapping(path = { "/play" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public GameResult play(@RequestBody ShapeType shape) {
		return new GameResult(game.doIWin(shape));
	}

}
