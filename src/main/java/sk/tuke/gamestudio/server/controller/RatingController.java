package sk.tuke.gamestudio.server.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tuke.gamestudio.server.entity.Comment;
import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.service.CommentException;
import sk.tuke.gamestudio.server.service.RatingException;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class RatingController extends GameController {
	
	

	

	@RequestMapping("/rating")
	
	public String rating(Rating rating, Model model){
		rating.setRatedon(new Timestamp(new Date().getTime()));
		rating.setPlayer(userController.getLoggedUser().getUsername());
		try {
			ratingService.setRating(rating);
		} catch (RatingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("game",rating.getGame());
		try {
			model.addAttribute("scores", scoreService.getBestScores(rating.getGame()));
			model.addAttribute("comments", commentService.getComments(rating.getGame()));
			model.addAttribute("ratings", ratingService.getRating(rating.getGame()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "game";
	}

}

