package kata.tennis.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Player;
import service.Game;

public class TennisGameTest {

	Player nadal;
	Player federer;
	Game game;

	@Before
	public void setUp() {
		this.nadal = new Player("Raphael", "NADAL", "FR", 26);
		this.federer = new Player("Roger", "FEDERER", "FR", 25);
		this.game = Game.getGameInstance(nadal, federer);
	}

	@Test
	public void testInitialScore() {
		this.nadal.setGameScore(0);
		this.federer.setGameScore(0);
		assertEquals("Correct Score: 0 - 0", "0 - 0", game.getScore());
	}

	@Test
	public void testOnePointWonPlayer1() {
		this.nadal.winPoint();
		assertEquals("Correct Score: 15 - 0", "15 - 0", game.getScore());
	}

	@Test
	public void testOnePointWonPlayer2() {
		this.federer.winPoint();
		assertEquals("Correct Score: 15 - 0", "0 - 15", game.getScore());
	}

	@Test
	public void testFifteenAllScore() {
		this.nadal.winPoint();
		this.federer.winPoint();
		assertEquals("Correct Score: 15 - 15", "15 - 15", game.getScore());
	}

	@Test
	public void testFifteenThirtyLoveScore() {
		this.nadal.winPoint();
		this.federer.winPoint();
		this.federer.winPoint();
		assertEquals("Correct Score: 15 - 30", "15 - 30", game.getScore());
	}

	@Test
	public void testThirtyAllScore() {
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		assertEquals("Correct Score: 30 - 30", "30 - 30", game.getScore());
	}

	@Test
	public void testFortyThirtyScore() {
		this.nadal.winPoint();
		this.nadal.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.federer.winPoint();
		assertEquals("Correct Score: 40 - 30", "40 - 30", this.game.getScore());
	}

	@Test
	public void testDeuceScore() {
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
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.nadal.winPoint();
		assertEquals("Correct Score: " + nadal.getFirstName() + " " + nadal.getLastName() + " won the game",
				nadal.getFirstName() + " " + nadal.getLastName() + " won the game", this.game.getScore());
	}

	@Test
	public void testPlayer2Won() {
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		this.nadal.winPoint();
		this.federer.winPoint();
		// Update Score After Deuce
		this.game.updateScoreAfterDeuce();
		this.federer.winPoint();
		this.federer.winPoint();
		// Update Score
		this.game.getScore();
		assertEquals("Number of game(s) won by " + federer.getFirstName() + " " + federer.getLastName(), 1,
				this.federer.getNbrGameWon());
	}

	@Test
	public void playGame() {
		this.game.playGame();
		assertEquals("Number of game(s) won by " + federer.getFirstName() + " " + federer.getLastName(), 1,
				this.federer.getNbrGameWon());
	}

}
