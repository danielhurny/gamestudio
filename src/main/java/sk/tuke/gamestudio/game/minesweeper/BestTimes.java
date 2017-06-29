package sk.tuke.gamestudio.game.minesweeper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {
	/** List of best player times. */
	private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

	/**
	 * Returns an iterator over a set of best times.
	 * 
	 * @return an iterator
	 */
	public Iterator<PlayerTime> iterator() {
		return playerTimes.iterator();
	}

	/**
	 * Adds player time to best times.
	 * 
	 * @param name
	 *            name ot the player
	 * @param time
	 *            player time in seconds
	 */
	public void addPlayerTime(String name, int time) {
		PlayerTime newPlayer = new PlayerTime(name, time);
		insertToDB(newPlayer);

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
		selectFromDB();
		Formatter f = new Formatter();
		playerTimes.forEach((p) -> f.format("%d. %s", playerTimes.indexOf(p) + 1, p.toString() + "\n"));

		// Formatter f = new Formatter();
		// playerTimes.forEach((p) -> f.format("%d. %s", playerTimes.indexOf(p)
		// + 1, p.toString()));
		// for (int i = 0; i < playerTimes.size(); i++) {
		// f.format("%d. %s %d", (i + 1), playerTimes.get(i).getName(),
		// playerTimes.get(i).getTime());
		// }
		return f.toString();
	}

	private void insertToDB(PlayerTime playerTime) {

		try (Connection connection = DriverManager.getConnection(DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD); Statement stm = connection.createStatement();) {

			try {
				stm.executeUpdate(DatabaseSetting.QUERY_CREATE_BEST_TIMES);
			} catch (Exception e) {
				// do not propagate exception, table may already exist
			}
			;
			PreparedStatement pstm = connection.prepareStatement(DatabaseSetting.QUERY_ADD_BEST_TIME);
			pstm.setString(1, playerTime.getName());
			pstm.setInt(2, playerTime.getTime());
			pstm.execute();
			pstm.close();

		} catch (SQLException e) {
			System.out.println("Exception occured during saving high score to database: " + e.getMessage());
			// TODO: handle exception
		}

	}

	private void selectFromDB() {
		try (Connection connection = DriverManager.getConnection(DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD); Statement stm = connection.createStatement();) {
			ResultSet rs = stm.executeQuery(DatabaseSetting.QUERY_SELECT_BEST_TIMES);
			playerTimes.clear();

			while (rs.next()) {
				PlayerTime pt = new PlayerTime(rs.getString(1), rs.getInt(2));
				playerTimes.add(pt);
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}
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
		/**
		 * porovna cas hracov
		 */
		public int compareTo(PlayerTime arg0) {

			return this.time - arg0.time;

		}

	}
}
