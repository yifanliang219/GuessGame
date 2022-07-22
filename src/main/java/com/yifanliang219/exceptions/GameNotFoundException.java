package com.yifanliang219.exceptions;

public class GameNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 5779766809161619486L;
	public String message;
	
	public GameNotFoundException(int gameId) {
		this.message = "No game found with id " + gameId;
	}

	public String getMessage() {
		return message;
	}

}
