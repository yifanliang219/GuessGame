package com.yifanliang219.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "guessNumberGame")
public class Game {

	@Id
	@GeneratedValue
	private int gameId;

	@Column
	private String answer;

	@Column
	private boolean finished;

	@OneToMany(mappedBy = "game", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Round> round;

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
