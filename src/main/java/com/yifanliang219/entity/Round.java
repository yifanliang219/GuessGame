package com.yifanliang219.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "round")
@IdClass(RoundId.class)
public class Round {

	@Id
	private int gameId;
	
	@Id
	private int round;
	
	@Column
	private String guess;
	
	@Column
	private String result;

	
	
	public Round() {
		
	}

	public Round(int gameId, int round, String guess, String result) {
		this.gameId = gameId;
		this.round = round;
		this.guess = guess;
		this.result = result;
	}

	public int getGameId() {
		return gameId;
	}

	public int getRound() {
		return round;
	}

	public String getGuess() {
		return guess;
	}

	public String getResult() {
		return result;
	}
	
	
	
	
}
