package sk.tuke.gamestudio.server.service;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sk.tuke.gamestudio.server.entity.Score;

public class ScoreServiceJDBC implements ScoreService {

	private DatabaseSettings ds=new DatabaseSettings();
	public static final String INSERT_QUERY = "INSERT INTO SCORE (player,game,points,playedon) " + "VALUES (?, ?,?,?)";
	public static final String GET_BEST_SCORES = "SELECT player,game,points,playedon FROM score WHERE game = ?";

	@Override
	public void addScore(Score score)throws ScoreException {
		try (Connection con = DriverManager.getConnection(ds.URL, ds.USER, ds.PASSWORD);
				PreparedStatement stm = con.prepareStatement(INSERT_QUERY);) {
			stm.setString(1, score.getPlayer());
			stm.setString(2, score.getGame());
			stm.setInt(3, score.getPoints());
			stm.setTimestamp(4, new Timestamp(score.getDate().getTime()));
			stm.executeUpdate();

		} catch (SQLException e) {
			throw new ScoreException("Vkladanie skore neprebehlo");
			
		}
		// TODO Auto-generated method stub

	}

	@Override
	public List<Score> getBestScores(String game) throws ScoreException {
		List<Score> score = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(ds.URL, ds.USER, ds.PASSWORD);
				PreparedStatement stm = con.prepareStatement(GET_BEST_SCORES);
				) {
			stm.setString(1, game);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Score sc = new Score(rs.getString(1), rs.getString(2), rs.getInt(3), getSQLCurrentDate());
				score.add(sc);
			}

		} catch (SQLException e) {
			throw new ScoreException("Vkladanie skore neprebehlo");
			// TODO: handle exception
		}

		// TODO Auto-generated method stub
		return score;
	}
	
	private java.sql.Date getSQLCurrentDate() {
		return new java.sql.Date(new Date().getTime());

}
	}
