package sk.tuke.gamestudio.server.service.serviceJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.service.DatabaseSettings;
import sk.tuke.gamestudio.server.service.RatingException;
import sk.tuke.gamestudio.server.service.RatingService;

public class RatingServiceJDBC implements RatingService {
	
	private DatabaseSettings ds = new DatabaseSettings();

	private static final String INSERT_RATING = "INSERT INTO RATING (player,game,rating,ratedon) "
			+ "VALUES (?, ?,?,?)";
	private static final String GET_AVERAGE_RATING = "SELECT AVG(rating) FROM RATING WHERE game=?";
	private static final String GET_RATING = "SELECT rating FROM RATING where game=? AND player = ?";

	@Override
	public void setRating(Rating rating) throws RatingException {

		try (Connection con = DriverManager.getConnection(ds.URL, ds.USER,
				ds.PASSWORD); PreparedStatement stm = con.prepareStatement(INSERT_RATING);) {
			stm.setString(1, rating.getPlayer());
			stm.setString(2, rating.getGame());
			stm.setInt(3, rating.getRating());
			stm.setTimestamp(4, new Timestamp(rating.getRatedon().getTime()));
			stm.executeUpdate();

		} catch (SQLException e) {
			throw new RatingException("Pridanie udajov neprebehlo");
		}

	}

	@Override
	public double getAverageRating(String game) throws RatingException {
		int rating = 0;
		try (Connection con = DriverManager.getConnection(ds.URL, ds.USER,
				ds.PASSWORD); PreparedStatement stm = con.prepareStatement(GET_AVERAGE_RATING);) {
			stm.setString(1, game);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				rating = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new RatingException("Vyber ratingu neprebehol");
		}
		return rating;
	}

	@Override
	public int getRating(String game, String player) throws RatingException {
		int rating = 0;
		try (Connection con = DriverManager.getConnection(ds.URL, ds.USER,
				ds.PASSWORD);

				PreparedStatement stm = con.prepareStatement(GET_RATING);) {
			stm.setString(1, game);
			stm.setString(2, player);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				rating = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new RatingException("Vyber ratingu neprebehol");
		}

		return rating;
	}

}
