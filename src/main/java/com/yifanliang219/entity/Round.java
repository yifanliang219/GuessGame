package com.yifanliang219.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "round")
public class Round {

	@EmbeddedId
	private RoundId roundId;

	@Column
	private String guess;

	@Column
	private String result;

	@MapsId("gameId")
	@ManyToOne
	@JoinColumn(name = "game_id", referencedColumnName = "gameId")
	private Game game;

	public Round() {

	}

	public Round(Game game, int round, String guess, String result) {
		this.roundId = new RoundId(game.getGameId(), round);
		this.guess = guess;
		this.result = result;
		this.game = game;
	}

	public int getGameId() {
		return roundId.getGameId();
	}

	public int getRound() {
		return roundId.getRound();
	}

	public String getGuess() {
		return guess;
	}

	public String getResult() {
		return result;
	}

}
