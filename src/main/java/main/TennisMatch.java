package main;

import org.apache.log4j.Logger;

import model.Player;
import service.Game;
import service.Match;
import service.Set;

public class TennisMatch {
	final static Logger logger = Logger.getLogger(TennisMatch.class);

	public static void main(String[] args) {
		Player player1 = new Player("Player1", "Lastname1", "FR", 26);
		Player player2 = new Player("Player2", "Lastname2", "FR", 25);
		Game game = Game.getGameInstance(player1, player2);
		Set set = Set.getSetInstance(game);
		Match match = new Match(3, set);
		match.playMatch();
		logger.debug(match.getSets().toString());
		logger.debug("Winner: " + match.getWinner().toString());
	}
}
