package sk.tuke.gamestudio.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.server.service.CommentService;
import sk.tuke.gamestudio.server.service.RatingService;
import sk.tuke.gamestudio.server.service.ScoreService;
import sk.tuke.gamestudio.server.service.StatisticService;
import sk.tuke.gamestudio.server.service.serviceJPA.CommentServiceJPA;
import sk.tuke.gamestudio.server.service.serviceJPA.RatingServiceJPA;
import sk.tuke.gamestudio.server.service.serviceJPA.ScoreServiceJPA;
import sk.tuke.gamestudio.server.service.serviceJPA.StatisticServiceJPA;
import sk.tuke.gamestudio.server.service.serviceJPA.UserService;
import sk.tuke.gamestudio.server.service.serviceJPA.UserServiceJPA;

@Configuration
@SpringBootApplication
public class GameStudioServer {

	public static void main(String[] args) {
		SpringApplication.run(GameStudioServer.class, args);
		
		// TODO Auto-generated method stub

	}
	@Bean
	public ScoreService scoreService(){
		return new ScoreServiceJPA();
	}
	@Bean
	public CommentService commentService(){
		return new CommentServiceJPA();
	}
	@Bean
	public RatingService ratingService(){
		return new RatingServiceJPA();
	}
	@Bean
	public UserService userService(){
		return new UserServiceJPA();
	}
	@Bean
	public StatisticService statisticService(){
		return new StatisticServiceJPA();
	}
	

}
