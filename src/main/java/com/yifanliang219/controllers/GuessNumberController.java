package com.yifanliang219.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yifanliang219.requests.GuessRequestBody;
import com.yifanliang219.responses.GameResponseBody;
import com.yifanliang219.services.GuessNumberService;

@RestController
public class GuessNumberController {

	@Autowired
	private GuessNumberService service;

	@PostMapping("/begin")
	public ResponseEntity<String> beginNewGame() {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.beginNewGame());
	}

	@PostMapping("/guess")
	public ResponseEntity<Object> makeAGuess(@RequestBody GuessRequestBody guessRequest) {
		try {
			return ResponseEntity.ok(service.makeAGuss(guessRequest));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/game")
	public List<GameResponseBody> getAllGames() {
		return service.getAllGames();
	}

	@GetMapping("/game/{gameId}")
	public ResponseEntity<Object> getAGame(@PathVariable int gameId) {
		try {
			return ResponseEntity.ok(service.getAGame(gameId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/rounds/{gameId}")
	public ResponseEntity<Object> getRoundsOfGame(@PathVariable int gameId) {
		try {
			return ResponseEntity.ok(service.getRoundsOfGame(gameId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("delete/{gameId}")
	public ResponseEntity<Object> deleteAGame(@PathVariable int gameId) {
		try {
			return ResponseEntity.ok(service.deleteAGame(gameId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
