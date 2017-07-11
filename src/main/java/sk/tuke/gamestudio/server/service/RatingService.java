package sk.tuke.gamestudio.server.service;

import java.util.List;

import sk.tuke.gamestudio.server.entity.Rating;

public interface RatingService {
    void setRating(Rating rating) throws RatingException;

    double getAverageRating(String game) throws RatingException;
		
    int getRating(String game, String player) throws RatingException;
	
    List<Rating> getRating(String game) throws RatingException;
}
