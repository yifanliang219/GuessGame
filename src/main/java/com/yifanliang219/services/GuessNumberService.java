package com.yifanliang219.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yifanliang219.entity.Game;
import com.yifanliang219.entity.Round;
import com.yifanliang219.exceptions.GameNotFoundException;
import com.yifanliang219.exceptions.GuessFormatException;
import com.yifanliang219.repo.GuessNumberGameRepo;
import com.yifanliang219.repo.RoundRepo;
import com.yifanliang219.requests.GuessRequestBody;

@Service
public class GuessNumberService {

	@Autowired
	private GuessNumberGameRepo gameRepo;

	@Autowired
	private RoundRepo roundRepo;

	public Game beginNewGame() {

		List<Integer> digits = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
		String answer = "";
		while (answer.length() < 4) {
			int index = (int) (Math.random() * digits.size());
			int digit = digits.get(index);
			answer += digit;
			digits.remove(index);
		}
		Game game = new Game(answer);
		gameRepo.save(game);
		return game;

	}

	public Round makeAGuss(GuessRequestBody guessRequest) {
		int gameId = guessRequest.getGameId();
		String guess = guessRequest.getGuess();
		if (guess.trim().length() != 4)
			throw new GuessFormatException(guess);
		Optional<Game> optionalGame = gameRepo.findById(gameId);
		if (optionalGame.isEmpty())
			throw new GameNotFoundException(gameId);
		Game game = optionalGame.get();
		String answer = game.getAnswer();
		int e = 0;
		int p = 0;
		for (int index = 0; index < guess.length(); index++) {
			try {
				char guessInt = guess.charAt(index);
				char answerInt = answer.charAt(index);
				if (guessInt == answerInt) {
					e++;
				} else if (answer.contains(guessInt + "")) {
					p++;
				}
			} catch (Exception exeption) {
				throw new GuessFormatException(guess);
			}
		}
		String result = String.format("e:%d:p:%d", e, p);
		if (e == 4) {
			game.setFinished(true);
			gameRepo.save(game);
		}
		int thisRound = gameRepo.getNumberOfRoundCompletedOfGame(gameId) + 1;
		Round round = new Round(game, thisRound, guess, result);
		roundRepo.save(round);
		return round;
	}

	public Iterable<Game> getAllGames() {

		Iterable<Game> games = gameRepo.findAll();
		return games;

	}

	public Game getAGame(int gameId) {
		Optional<Game> optionalGame = gameRepo.findById(gameId);
		if (optionalGame.isEmpty())
			throw new GameNotFoundException(gameId);
		Game game = optionalGame.get();
		return game;
	}

	public List<Round> getRoundsOfGame(int gameId) {
		Optional<Game> optionalGame = gameRepo.findById(gameId);
		if (optionalGame.isEmpty())
			throw new GameNotFoundException(gameId);
		return gameRepo.getRoundsOfGame(gameId, Sort.by(Direction.ASC, "gameId"));
	}
	
	public Game deleteAGame(int gameId) {
		Optional<Game> optionalGame = gameRepo.findById(gameId);
		if (optionalGame.isEmpty())
			throw new GameNotFoundException(gameId);
		gameRepo.deleteById(gameId);
		return optionalGame.get();
	}
	
	public void deleteAllGames() {
		gameRepo.deleteAll();
	}

}
