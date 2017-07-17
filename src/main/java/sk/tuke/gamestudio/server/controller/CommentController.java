package sk.tuke.gamestudio.server.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tuke.gamestudio.server.entity.Comment;
import sk.tuke.gamestudio.server.service.CommentException;


@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class CommentController extends GameController {
	
	

	

	@RequestMapping("/comment")
	
	public String comment(Comment comment, Model model){
		comment.setDate(new Timestamp(new Date().getTime()));
		comment.setPlayer(userController.getLoggedUser().getUsername());
		try {
			commentService.addComment(comment);
		} catch (CommentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("game",comment.getGame());
		try {
			model.addAttribute("scores", scoreService.getBestScores(comment.getGame()));
			model.addAttribute("comments", commentService.getComments(comment.getGame()));
			model.addAttribute("ratings", ratingService.getRating(comment.getGame()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "game";
	}

}
