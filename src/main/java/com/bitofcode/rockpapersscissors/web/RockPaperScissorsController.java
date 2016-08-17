package com.bitofcode.rockpapersscissors.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bitofcode.rockpapersscissors.game.Game;
import com.bitofcode.rockpapersscissors.game.ShapeInvalidException;
import com.bitofcode.rockpapersscissors.game.ShapeType;

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
	public ResponseEntity<?> play(@RequestBody GameInput gameInput) {
		GameResult gameResult = null;
		try{
		ShapeType shape = convertToShapeType(gameInput);
		gameResult = new GameResult(game.play(shape));
		return new ResponseEntity<GameResult>(gameResult, HttpStatus.OK);
		} catch (ShapeInvalidException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	private ShapeType convertToShapeType(GameInput gameInput) {
		if (gameInput == null || gameInput.getGuess() == null)
			throw ShapeInvalidException.createShapeInvalidException("Game input is null.");
		try {
			return ShapeType.valueOf(gameInput.getGuess().toUpperCase());
		} catch (IllegalArgumentException e) {
			throw ShapeInvalidException.createShapeInvalidException("[" + gameInput.getGuess() + "] is invalid.");
		}
	}

}
