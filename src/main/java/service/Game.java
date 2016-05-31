package service;

import java.util.Random;

import org.apache.log4j.Logger;

import model.Player;

public class Game {
	private static Game gameInstance = null;
	final static Logger logger = Logger.getLogger(Game.class);
	private Player player1;
	private Player player2;
	private boolean gameEnded;

	private Game() {
	}

	public static Game getGameInstance(Player player1, Player player2) {
		if (gameInstance == null) {
			gameInstance = new Game();
			gameInstance.player1 = player1;
			gameInstance.player2 = player2;
			gameInstance.gameEnded = false;
		}
		return gameInstance;
	}

	public String getScore() {
		// Equality
		if (player1.getGameScore() == player2.getGameScore())
			switch (player1.getGameScore()) {
			case 0:
				return "0 - 0";
			case 1:
				return "15 - 15";
			case 2:
				return "30 - 30";
			case 3:
				return "40 - 40";
			case 4:
				updateScoreAfterDeuce();
				return "40 - 40";

			}
		// Deuce
		if (player1.getGameScore() >= 3 && player2.getGameScore() >= 3) {
			// Av to player1
			if (player1.getGameScore() == 4 && player2.getGameScore() == 3)
				return "AV " + player1.getFirstName() + " " + player1.getLastName();
			// Av to player2
			if (player1.getGameScore() == 3 && player2.getGameScore() == 4)
				return "AV " + player2.getFirstName() + " " + player2.getLastName();
			// Point + AV Player1
			if (player1.getGameScore() == 5 && player2.getGameScore() == 3) {
				winGame(player1);
				return player1.getFirstName() + " " + player1.getLastName() + " won the game";
			}
			// Point + AV Player2
			if (player1.getGameScore() == 3 && player2.getGameScore() == 5) {
				winGame(player2);
				return player2.getFirstName() + " " + player2.getLastName() + " won the game";
			}
		} else {
			if (player1.getGameScore() == 4) {
				winGame(player1);
				return player1.getFirstName() + " " + player1.getLastName() + " won the game";
			}
			if (player2.getGameScore() == 4) {
				winGame(player2);
				return player2.getFirstName() + " " + player2.getLastName() + " won the game";
			}
		}
		return decipherScore(player1.getGameScore()) + " - " + decipherScore(player2.getGameScore());
	}

	public int decipherScore(int score) {
		switch (score) {
		case 0:
			return 0;
		case 1:
			return 15;
		case 2:
			return 30;
		default:
			return 40;
		}
	}

	public void updateScoreAfterDeuce() {
		player1.setGameScore(3);
		player2.setGameScore(3);
	}

	public void winGame(Player player) {
		this.gameEnded = true;
		player1.setGameScore(0);
		player2.setGameScore(0);
		player.setNbrGameWon(player.getNbrGameWon() + 1);
	}

	public void initializeNbrGameWon() {
		player1.setNbrGameWon(0);
		player2.setNbrGameWon(0);
	}

	public void initializeGameScore() {
		player1.setGameScore(0);
		player2.setGameScore(0);
	}

	public void RandomPlayerWinPoint() {
		Random random = new java.util.Random();
		int tmp = random.nextInt(2) + 1;
		switch (tmp) {
		case 1:
			this.player1.winPoint();
			break;
		case 2:
			this.player2.winPoint();
			break;
		}
	}

	public void playGame() {
		while (!this.gameEnded) {
			RandomPlayerWinPoint();
			logger.debug(getScore());
		}
		this.setGameEnded(false);
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public boolean isGameEnded() {
		return gameEnded;
	}

	public void setGameEnded(boolean gameEnded) {
		this.gameEnded = gameEnded;
	}

}
