package sk.tuke.gamestudio.game.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Player times.
 */
public class BestTimesSet {
	/** List of best player times. */
	private Set<PlayerTime> playerTimes = new HashSet<>();

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
		playerTimes.add(new PlayerTime(name, time));

	}

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object
	 */
	public String toString() {
		// StringBuilder sb=new StringBuilder();
		// playerTimes.forEach((p)->sb.append(playerTimes.indexOf(p) +
		// 1).append(". ").append(p.toString()));

		Formatter f = new Formatter();
		playerTimes.forEach((p) -> f.format("%s %d", p.getName(), p.getTime()));
		// for (int i = 0; i < playerTimes.size(); i++) {
		// f.format("%d. %s %d", (i + 1), playerTimes.get(i).getName(),
		// playerTimes.get(i).getTime());
		// }
		return f.toString();
	}

	public void reset() {
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

		@Override
		public int hashCode() {
			int result = 0;
			for (int i = 0; i < name.length(); i++) {
				result *= name.charAt(i);
			}
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null||getClass() != obj.getClass())
				return false;
			PlayerTime other = (PlayerTime) obj;
			return (name.equals(other.getName()) && time == other.getTime()) ;
		
		}

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

		public String toString() {
			return name + " " + time;
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
