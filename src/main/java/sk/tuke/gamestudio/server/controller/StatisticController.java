package sk.tuke.gamestudio.server.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class StatisticController extends GameController {
	
	
	
	
    @RequestMapping({"/statistic"})
    public String statistic(Model model) {
    	try {
			model.addAttribute("ratingStones", ratingService.getAverageRating("stones"));
			model.addAttribute("ratingMinesweeper", ratingService.getAverageRating("minesweeper"));
			model.addAttribute("ratingPexeso", ratingService.getAverageRating("pexeso"));
			model.addAttribute("statisticPexeso", statisticService.getStatistic("pexeso"));
			model.addAttribute("statisticMinesweeper", statisticService.getStatistic("minesweeper"));
			model.addAttribute("statisticStones", statisticService.getStatistic("stones"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "statistic";
    }

}
