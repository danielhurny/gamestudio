package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sk.tuke.gamestudio.server.service.RatingException;
import sk.tuke.gamestudio.server.service.RatingService;
import sk.tuke.gamestudio.server.service.ScoreException;

//http://localhost:8080
@Controller
public class IndexController {
	
	@Autowired
	private RatingService ratingService;
	
	
    @RequestMapping({"", "/index"})
    public String index(Model model) {
    	try {
			model.addAttribute("ratingKamene", ratingService.getAverageRating("kamene"));
			model.addAttribute("ratingMinesweeper", ratingService.getAverageRating("minesweeper"));
			model.addAttribute("ratingPexeso", ratingService.getAverageRating("pexeso"));
		} catch (RatingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "index";
    }
}
