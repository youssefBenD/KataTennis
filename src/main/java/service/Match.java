package service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import model.Player;

public class Match {
	final static Logger logger = Logger.getLogger(Match.class);
	private int nbrSet;
	private HashMap<String, String> sets;
	private Set set;

	public Match(int nbrSet, Set set) {
		super();
		this.nbrSet = nbrSet;
		this.sets = new HashMap<String, String>();
		this.set = set;
	}

	public void playMatch() {
		for (int i = 0; i < nbrSet; i++) {
			logger.debug("********** SET:" + String.valueOf(i + 1) + " **********");
			this.set.playSet();
			sets.put("SET" + String.valueOf(i + 1), this.set.getScore());
		}
	}

	public Player getWinner() {
		int nbrSetPlayer1 = 0;
		int nbrSetPlayer2 = 0;
		Iterator<Map.Entry<String, String>> iterator = this.sets.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> setScore = iterator.next();
			String[] score = setScore.getValue().split("-");
			if (Integer.parseInt(score[0].replace(" ", "")) > Integer.parseInt(score[1].replace(" ", "")))
				nbrSetPlayer1++;
			else
				nbrSetPlayer2++;
			iterator.remove();
		}
		if (nbrSetPlayer1 > nbrSetPlayer2)
			return this.getSet().getGame().getPlayer1();
		else
			return this.getSet().getGame().getPlayer2();
	}

	public int getNbrSet() {
		return nbrSet;
	}

	public void setNbrSet(int nbrSet) {
		this.nbrSet = nbrSet;
	}

	public HashMap<String, String> getSets() {
		return sets;
	}

	public void setSets(HashMap<String, String> sets) {
		this.sets = sets;
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

}
