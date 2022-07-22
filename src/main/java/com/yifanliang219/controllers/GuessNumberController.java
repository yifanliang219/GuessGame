package com.yifanliang219.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yifanliang219.entity.Round;
import com.yifanliang219.requests.GuessRequestBody;
import com.yifanliang219.responses.GameResponseBody;
import com.yifanliang219.services.GuessNumberService;

@RestController
public class GuessNumberController {
	
	@Autowired
	private GuessNumberService service;
	
	@PostMapping("/begin")
	public ResponseEntity<String> beginNewGame() {
		return service.beginNewGame();
	}
	
	@PostMapping("/guess")
	public Round makeAGuess(@RequestBody GuessRequestBody guessRequest) {
		return service.makeAGuss(guessRequest);
	}
	
	@GetMapping("/game")
	public List<GameResponseBody> getAllGames(){
		return service.getAllGames();
	}
	
	@GetMapping("/game/{gameId}")
	public GameResponseBody getAGame(@PathVariable int gameId) {
		return service.getAGame(gameId);
	}
	
}
