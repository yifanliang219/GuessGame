package com.yifanliang219.exceptions;

public class GuessFormatException extends RuntimeException{
	
	private static final long serialVersionUID = 5019662525735188647L;
	private String message;
	
	public GuessFormatException(String guess) {
		this.message = "The guess \"" + guess + "\" is in the wrong format (Must be a 4 digit number).";
	}

	public String getMessage() {
		return message;
	}
	
	

}
