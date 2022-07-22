package com.yifanliang219.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="guessNumberGame")
public class Game {
	
	@Id
	@GeneratedValue
	private int gameId;
	
	@Column
	private String answer;
	
	@Column
	private boolean finished;
	
	public Game() {
		
	}
	
	public Game(String answer) {
		this.answer = answer;
	}

	public int getGameId() {
		return gameId;
	}

	public String getAnswer() {
		return answer;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	

}
