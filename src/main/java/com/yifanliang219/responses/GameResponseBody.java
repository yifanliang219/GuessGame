package com.yifanliang219.responses;

import com.yifanliang219.entity.Game;

public class GameResponseBody {

	private int gameId;
	private String answer;
	private boolean finished;
	
	public GameResponseBody(Game game) {
		this.gameId = game.getGameId();
		this.answer = "hidden";
		this.finished = game.isFinished();
		if(isFinished()) {
			this.answer = game.getAnswer();
		}
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
	
	
	
}
