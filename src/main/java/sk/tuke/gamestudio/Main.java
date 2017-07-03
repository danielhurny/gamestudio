package sk.tuke.gamestudio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sk.tuke.gamestudio.game.UserInterface;
import sk.tuke.gamestudio.game.kamene.consoleui.ConsoleUiKamene;
import sk.tuke.gamestudio.game.kamene.core.FieldKamene;
import sk.tuke.gamestudio.game.minesweeper.consoleui.ConsoleUiMinesweeper;
import sk.tuke.gamestudio.game.minesweeper.core.FieldMinesweeper;
import sk.tuke.gamestudio.server.service.CommentService;
import sk.tuke.gamestudio.server.service.CommentServiceJPA;
import sk.tuke.gamestudio.server.service.CommentServiceSORM;
import sk.tuke.gamestudio.server.service.RatingService;
import sk.tuke.gamestudio.server.service.RatingServiceJPA;
import sk.tuke.gamestudio.server.service.RatingServiceSORM;
import sk.tuke.gamestudio.server.service.ScoreService;
import sk.tuke.gamestudio.server.service.ScoreServiceJPA;
import sk.tuke.gamestudio.server.service.ScoreServiceSORM;

@Configuration
@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
		

		SpringApplication.run(Main.class, args);

	}

	@Bean
	

	public CommandLineRunner runner(GameStudioUI ui) {
		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {

				ui.play();
			}

		};
	}

	@Bean
	public GameStudioUI gameStudioUI() {
		return new GameStudioUI();
	}

	@Bean
	public UserInterface consoleUiKamene(FieldKamene field) {
		return new ConsoleUiKamene(field);
	}
	@Bean
	public UserInterface consoleUiMinesweeper(FieldMinesweeper field) {
		return new ConsoleUiMinesweeper(field);
	}

	@Bean
	public FieldKamene fieldKamene() {
		return new FieldKamene(3, 3);

	}

	@Bean
	public FieldMinesweeper fieldMinesweeper() {
		return new FieldMinesweeper(9, 9, 1);
	}

	@Bean
	public CommentService commentservice() {
//		return new CommentServiceJDBC();
//		return new CommentServiceSORM();
		return new CommentServiceJPA();
	}

	@Bean
	public RatingService ratingservice() {
//		return new RatingServiceJDBC();
//		return new RatingServiceSORM();
		return new RatingServiceJPA();
	}

	@Bean
	public ScoreService scoreservice() {
//		return new ScoreServiceJDBC();
//		return new ScoreServiceSORM();
		return new ScoreServiceJPA();
	}
	
	@Bean
	public List<UserInterface> games(UserInterface... ui) {
		List<UserInterface> games = new ArrayList<UserInterface>();
		for(UserInterface u : ui){
			games.add(u);
		}
		return games;
	}
}
