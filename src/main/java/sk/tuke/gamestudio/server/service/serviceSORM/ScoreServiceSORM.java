package sk.tuke.gamestudio.server.service.serviceSORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.DatabaseSettings;
import sk.tuke.gamestudio.server.service.ScoreException;
import sk.tuke.gamestudio.server.service.ScoreService;
import sk.tuke.gamestudio.sorm.SORM;

public class ScoreServiceSORM implements ScoreService {

	DatabaseSettings ds = new DatabaseSettings();
	@Override
	public void addScore(Score score) throws ScoreException {
		try (Connection connection = DriverManager.getConnection(ds.URL, ds.USER,
				ds.PASSWORD);) {

			SORM sorm = new SORM(connection);
			sorm.insert(score);

		} catch (Exception e) {
			throw new ScoreException(e.getMessage());
		}
	}

	@Override
	public List<Score> getBestScores(String game) throws ScoreException {
		try (Connection connection = DriverManager.getConnection(ds.URL, ds.USER,
				ds.PASSWORD);) {

			SORM sorm = new SORM(connection);
			return sorm.select(Score.class, "where game = '" + game + "'");

		} catch (Exception e) {
			throw new ScoreException(e.getMessage());
		}
	}

}
