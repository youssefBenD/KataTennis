package kata.tennis.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import model.Player;
import service.Game;

public class TennisGameTest {

	Player nadal;
	Player federer;

	@Before
	public void setUp() {
		this.nadal = new Player("Raphael", "NADAL", "FR", 26);
		this.federer = new Player("Roger", "FEDERER", "FR", 25);

	}

	@Before
	public void resetSingleton()
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field instance = Game.class.getDeclaredField("gameInstance");
		instance.setAccessible(true);
		instance.set(null, null);
	}

	@Test
	public void testInitialScore() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.setGameScore(0);
		this.federer.setGameScore(0);
		assertEquals("Correct Score: 0 - 0", "0 - 0", game.getScore());
	}

	@Test
	public void testOnePointWonPlayer1() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.winPoint();
		assertEquals("Correct Score: 15 - 0", "15 - 0", game.getScore());
	}

	@Test
	public void testOnePointWonPlayer2() {
		Game game = Game.getGameInstance(nadal, federer);
		this.federer.winPoint();
		assertEquals("Correct Score: 15 - 0", "0 - 15", game.getScore());
	}

	@Test
	public void testFifteenAllScore() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.winPoint();
		this.federer.winPoint();
		assertEquals("Correct Score: 15 - 15", "15 - 15", game.getScore());
	}

	@Test
	public void testFifteenThirtyLoveScore() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.winPoint();
		this.federer.winPoint();
		this.federer.winPoint();
		assertEquals("Correct Score: 15 - 30", "15 - 30", game.getScore());
	}

	@Test
	public void testThirtyAllScore() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		assertEquals("Correct Score: 30 - 30", "30 - 30", game.getScore());
	}

	@Test
	public void testFortyThirtyScore() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.winPoint();
		this.nadal.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.federer.winPoint();
		assertEquals("Correct Score: 40 - 30", "40 - 30", game.getScore());
	}

	@Test
	public void testDeuceScore() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		assertEquals("Correct Score: Deuce", "40 - 40", game.getScore());
	}

	@Test
	public void testAvPlayer1Score() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		assertEquals("Correct Score: Deuce", "AV " + this.nadal.getFirstName() + " " + this.nadal.getLastName(),
				game.getScore());
	}

	@Test
	public void testAvPlayer2Score() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.federer.winPoint();
		assertEquals("Correct Score: Deuce", "AV " + this.federer.getFirstName() + " " + this.federer.getLastName(),
				game.getScore());
	}

	@Test
	public void testDeuceAfterAvScore() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		assertEquals("Correct Score: Deuce", "40 - 40", game.getScore());
	}

	@Test
	public void testPlayer1Won() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.nadal.winPoint();
		assertEquals("Correct Score: " + nadal.getFirstName() + " " + nadal.getLastName() + " won the game",
				nadal.getFirstName() + " " + nadal.getLastName() + " won the game", game.getScore());
	}

	@Test
	public void testPlayer2Won() {
		Game game = Game.getGameInstance(nadal, federer);
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		// Update Score After Deuce
		game.updateScoreAfterDeuce();
		this.federer.winPoint();
		this.federer.winPoint();
		// Update Score
		game.getScore();
		assertEquals("Number of game(s) won by " + federer.getFirstName() + " " + federer.getLastName(), 1,
				this.federer.getNbrGameWon());
	}

	// @Test
	// public void playGame() {
	// Game game = Game.getGameInstance(nadal, federer);
	// game.playGame();
	// assertEquals("Number of game(s) won by " + federer.getFirstName() + " " +
	// federer.getLastName(), 1,
	// this.federer.getNbrGameWon());
	// }

}
