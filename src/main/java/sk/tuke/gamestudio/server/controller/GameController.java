package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import sk.tuke.gamestudio.server.service.CommentException;
import sk.tuke.gamestudio.server.service.CommentService;
import sk.tuke.gamestudio.server.service.RatingException;
import sk.tuke.gamestudio.server.service.RatingService;
import sk.tuke.gamestudio.server.service.ScoreException;
import sk.tuke.gamestudio.server.service.ScoreService;
import sk.tuke.gamestudio.server.service.StatisticService;

public abstract class GameController  {
	
	@Autowired
	protected ScoreService scoreService;
	@Autowired
	protected CommentService commentService;
	@Autowired
	protected RatingService ratingService;
	@Autowired
	protected UserController userController;
	@Autowired
	protected StatisticService statisticService;
	
	protected String message;
	
	public void setDataToModel(String game, Model model) {
		 	try {
		 			model.addAttribute("scores", scoreService.getBestScores(game));
		 		} catch (ScoreException e) {
		 			e.printStackTrace();
		 		}
		 		try {
		 			model.addAttribute("comments", commentService.getComments(game));
		 		} catch (CommentException e) {
		 			e.printStackTrace();
		 		}
		 		try {
		 			model.addAttribute("ratings", ratingService.getRating(game));
		 		} catch (RatingException e) {
		 			e.printStackTrace();
		 		}
		 	}

    
}
