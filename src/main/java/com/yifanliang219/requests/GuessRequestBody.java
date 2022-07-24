package com.yifanliang219.requests;

public class GuessRequestBody {

	private int gameId;
	private String guess;
	
	public GuessRequestBody() {
		
	}

	public GuessRequestBody(int gameId, String guess) {
		this.gameId = gameId;
		this.guess = guess;
	}

	public int getGameId() {
		return gameId;
	}
	
	public String getGuess() {
		return guess;
	}
	
	
	
}
