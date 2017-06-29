package sk.tuke.gamestudio.game.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Player times.
 */
public class BestTimesMap  {
	/** List of best player times. */
	private Map<String, PlayerTime> playerTimes = new HashMap<>();

	/**
	 * Returns an iterator over a set of best times.
	 * 
	 * @return an iterator
	 */
	
	

	/**
	 * Adds player time to best times.
	 * 
	 * @param name
	 *            name ot the player
	 * @param time
	 *            player time in seconds
	 */
	public void addPlayerTime(String name, int time) {
		playerTimes.put(name, new PlayerTime(name, time) );
		
	}

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object
	 */
	public String toString() {
//		StringBuilder sb=new StringBuilder();
//		playerTimes.forEach((p)->sb.append(playerTimes.indexOf(p) + 1).append(". ").append(p.toString()));
		
		Formatter f = new Formatter();
		playerTimes.keySet().forEach((p) -> f.format("%s", playerTimes.get(p).getName(), playerTimes.get(p).getTime()));
//		for (int i = 0; i < playerTimes.size(); i++) {
//			f.format("%d. %s %d", (i + 1), playerTimes.get(i).getName(), playerTimes.get(i).getTime());
//		}
		return f.toString();
	}
	
	public void reset(){
		playerTimes.clear();
	}

	/**
	 * Player time.
	 */
	public static class PlayerTime implements Comparable<PlayerTime> {
		/** Player name. */
		private final String name;

		/** Playing time in seconds. */
		private final int time;

		/**
		 * Constructor.
		 * 
		 * @param name
		 *            player name
		 * @param time
		 *            playing game time in seconds
		 */
		public PlayerTime(String name, int time) {
			this.name = name;
			this.time = time;
		}

		public String getName() {
			return name;
		}

		public int getTime() {
			return time;
		}
		
		public String toString(){
			return name+" "+time;
		}

		@Override
		/**
		 * porovna cas hracov
		 */
		public int compareTo(PlayerTime arg0) {

			return this.time - arg0.time;

		}

	}
}

