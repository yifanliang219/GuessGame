package com.yifanliang219;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yifanliang219.entity.Game;
import com.yifanliang219.entity.Round;
import com.yifanliang219.exceptions.GameNotFoundException;
import com.yifanliang219.requests.GuessRequestBody;
import com.yifanliang219.services.GuessNumberService;

@SpringBootTest
class GuessNumberApplicationTests {

	Game game1;
	Game game2;
	Game game3;
	String answer1;
	String answer2;
	String answer3;

	@Autowired
	GuessNumberService service;

	private String generateRandomGuss() {
		List<Integer> digits = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
		String guess = "";
		while (guess.length() < 4) {
			int index = (int) (Math.random() * digits.size());
			int digit = digits.get(index);
			guess += digit;
			digits.remove(index);
		}
		return guess;
	}

	private void updateGameFromDatabase() {
		game1 = service.getAGame(game1.getGameId());
		game2 = service.getAGame(game2.getGameId());
		game3 = service.getAGame(game3.getGameId());
	}

	@BeforeEach
	public void setup() {
		service.deleteAllGames();
		game1 = service.beginNewGame();
		game2 = service.beginNewGame();
		game3 = service.beginNewGame();
		answer1 = game1.getAnswer();
		answer2 = game2.getAnswer();
		answer3 = game3.getAnswer();
	}

	@AfterEach
	public void erase() {
		service.deleteAllGames();
	}

	@Test
	public void testGameId() {
		int id1 = game1.getGameId();
		int id2 = game2.getGameId();
		int id3 = game3.getGameId();
		assertEquals(id2, id1 + 1);
		assertEquals(id3, id2 + 1);
	}

	@Test
	public void testAnswer() {
		// test length
		assertEquals(4, answer1.length());
		assertEquals(4, answer2.length());
		assertEquals(4, answer3.length());
		// test if is digit
		Integer.parseInt(answer1);
		Integer.parseInt(answer2);
		Integer.parseInt(answer3);
		// test uniqueness of digits
		List<String> answers = Arrays.asList(answer1, answer2, answer3);
		Set<Character> digits = new HashSet<>();
		answers.forEach(answer -> {
			for (char c : answer.toCharArray()) {
				digits.add(c);
			}
			digits.clear();
		});
	}

	@Test
	public void testGuessAndRound() {
		String guess1 = generateRandomGuss();
		String guess2 = generateRandomGuss();
		String guess3 = generateRandomGuss();
		Pattern guessResultPattern = Pattern.compile("e:\\d:p:\\d");
		List<String> guessStrings = Arrays.asList(guess1, guess2, guess3);
		List<Game> games = Arrays.asList(game1, game2, game3);
		guessStrings.forEach(string -> {
			games.forEach(game -> {
				int gameId = game.getGameId();
				GuessRequestBody request = new GuessRequestBody(gameId, string);
				Round round = service.makeAGuss(request);
				assertEquals(gameId, round.getGameId());
				assertEquals(string, round.getGuess());
				Matcher guessMatcher = guessResultPattern.matcher(round.getResult());
				assertTrue(guessMatcher.find());
			});
		});
	}

	@Test
	public void testPerfectGuess() {
		GuessRequestBody guess1 = new GuessRequestBody(game1.getGameId(), game1.getAnswer());
		GuessRequestBody guess2 = new GuessRequestBody(game2.getGameId(), game2.getAnswer());
		GuessRequestBody guess3 = new GuessRequestBody(game3.getGameId(), game3.getAnswer());
		service.makeAGuss(guess1);
		service.makeAGuss(guess2);
		service.makeAGuss(guess3);
		updateGameFromDatabase();
		assertTrue(game1.isFinished());
		assertTrue(game2.isFinished());
		assertTrue(game3.isFinished());
	}

	@Test
	public void testGetAGame() {
		Game getGame1 = service.getAGame(game1.getGameId());
		Game getGame2 = service.getAGame(game2.getGameId());
		Game getGame3 = service.getAGame(game3.getGameId());
		assertEquals(getGame1.getAnswer(), game1.getAnswer());
		assertEquals(getGame2.getAnswer(), game2.getAnswer());
		assertEquals(getGame3.getAnswer(), game3.getAnswer());
	}

	@Test
	public void testDelete() {

		service.deleteAGame(game1.getGameId());
		List<Integer> gameIds = new ArrayList<>();
		service.getAllGames().forEach(game -> gameIds.add(game.getGameId()));
		assertEquals(2, gameIds.size());
		assertTrue(gameIds.contains(game2.getGameId()));
		assertTrue(gameIds.contains(game3.getGameId()));
		assertFalse(gameIds.contains(game1.getGameId()));
		assertThrows(GameNotFoundException.class, () -> {
			service.getAGame(game1.getGameId());
		});

	}

}
