package sk.tuke.gamestudio.server.service.serviceSORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.service.DatabaseSettings;
import sk.tuke.gamestudio.server.service.RatingException;
import sk.tuke.gamestudio.server.service.RatingService;
import sk.tuke.gamestudio.server.service.serviceJDBC.RatingServiceJDBC;
import sk.tuke.gamestudio.sorm.SORM;

public class RatingServiceSORM implements RatingService{
 private DatabaseSettings ds = new DatabaseSettings();
	
	@Override
	public void setRating(Rating rating) throws RatingException {
		try(Connection connection = DriverManager.getConnection(ds.URL, ds.USER, ds.PASSWORD);){
			
			SORM sorm = new SORM(connection);
			List<Rating> ratings = sorm.select(Rating.class, 
					"where game = '" + rating.getGame() + "' and player = '" + rating.getPlayer() + "'");
			if(ratings.isEmpty()){
				sorm.insert(rating); 
			} else{
				if(ratings.size() == 1){
					rating.setId(ratings.get(0).getId());
					sorm.update(rating);
				} else 
					throw new RatingException("Neocakavana chyba: v tabulke rating je viac "
							+ "ako jeden zaznam od toho isteho hraca pre jednu hru.");
			}
			
		} catch (Exception e) {
			throw new RatingException(e.getMessage());
		} 
	}

	@Override
	public double getAverageRating(String game) throws RatingException {
		return new RatingServiceJDBC().getAverageRating(game);
	}

	@Override
	public int getRating(String game, String player) throws RatingException {
		return new RatingServiceJDBC().getRating(game, player);
	}

}




































