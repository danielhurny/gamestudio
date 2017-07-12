package sk.tuke.gamestudio.server.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.RatingException;
import sk.tuke.gamestudio.server.service.RatingService;
import sk.tuke.gamestudio.server.service.ScoreException;

@Path("/rating")
public class RatingRestService {
	@Autowired
	RatingService ratingService;

	@POST
	@Consumes("application/json")
	public Response addRating(Rating rating) throws RatingException {
		ratingService.setRating(rating);
		return Response.ok().build();
	}

	@GET
	@Path("/{game}/{player}")
	@Produces("application/json")
	public int getRating(@PathParam("game") String game, @PathParam("player") String player) throws RatingException {
		return ratingService.getRating(game.toLowerCase(), player);
	}

	@GET
	@Path("/{game}")
	@Produces("application/json")
	public double getAverageRating(@PathParam("game") String game) throws RatingException {
		return ratingService.getAverageRating(game.toLowerCase());
	}

}
