package com.yifanliang219.entity;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class RoundId implements Serializable {
	
	private static final long serialVersionUID = -1413041923828339946L;
	private int gameId;
	private int round;
	
	public RoundId() {
		
	}
	
	public RoundId(int gameId, int round) {
		this.gameId = gameId;
		this.round = round;
	}

	public int getGameId() {
		return gameId;
	}

	public int getRound() {
		return round;
	}
	
	

}
