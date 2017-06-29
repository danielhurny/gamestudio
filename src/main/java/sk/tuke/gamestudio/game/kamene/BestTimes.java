package sk.tuke.gamestudio.game.kamene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BestTimes {

	private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

	private Set<PlayerTime> playersTimesSet = new HashSet<>();

	public void addPlayerTime(String name, int time) {
		playersTimesSet.add(new PlayerTime(name, time));

		playerTimes.add(new PlayerTime(name, time));
		Collections.sort(playerTimes);
	}

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object
	 */
	public String toString() {

		Formatter f = new Formatter();
		playerTimes.forEach((p) -> f.format("%d. %s", playerTimes.indexOf(p) + 1, p.toString()));
	
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
		public int hashCode() {
			int result = 0;
			for (int i = 0; i < name.length(); i++) {
				result += Character.getNumericValue(name.charAt(i));
			}
			return result;
		}

		@Override
		public boolean equals(Object obj) {

			if (obj == null || getClass() != obj.getClass())
				return false;
			PlayerTime other = (PlayerTime) obj;
			return (name.equals(other.name) && time == other.time);
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
