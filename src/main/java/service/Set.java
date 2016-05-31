package service;

import org.apache.log4j.Logger;

public class Set {
	private static Set setInstance = null;
	final static Logger logger = Logger.getLogger(Set.class);
	private String score;
	private Game game;

	private Set() {
	}

	public static Set getSetInstance(Game game) {
		if (setInstance == null) {
			setInstance = new Set();
			setInstance.score = "0 - 0";
			setInstance.game = game;
		}
		return setInstance;
	}

	public void playSet() {
		while (this.game.getPlayer1().getNbrGameWon() < 6 && this.game.getPlayer2().getNbrGameWon() < 6) {
			this.game.playGame();
		}
		this.score = String.valueOf(this.game.getPlayer1().getNbrGameWon()) + " - "
				+ String.valueOf(this.game.getPlayer2().getNbrGameWon());
		logger.debug(this.score);
		this.game.initializeNbrGameWon();
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
