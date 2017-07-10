package sk.tuke.gamestudio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sk.tuke.gamestudio.game.UserInterface;
import sk.tuke.gamestudio.game.kamene.consoleui.ConsoleUiKamene;
import sk.tuke.gamestudio.game.kamene.core.FieldKamene;
import sk.tuke.gamestudio.game.minesweeper.consoleui.ConsoleUiMinesweeper;
import sk.tuke.gamestudio.game.minesweeper.consoleui.ReplayConsoleUI;
import sk.tuke.gamestudio.game.minesweeper.core.FieldMinesweeper;
import sk.tuke.gamestudio.server.service.CommentService;
import sk.tuke.gamestudio.server.service.CommentServiceRestClient;
import sk.tuke.gamestudio.server.service.GamePlayService;
import sk.tuke.gamestudio.server.service.GamePlayServiceJPA;
import sk.tuke.gamestudio.server.service.RatingService;
import sk.tuke.gamestudio.server.service.RatingServiceRestClient;
import sk.tuke.gamestudio.server.service.ScoreService;
import sk.tuke.gamestudio.server.service.ScoreServiceRestClient;
import sk.tuke.gamestudio.server.service.serviceJPA.CommentServiceJPA;
import sk.tuke.gamestudio.server.service.serviceJPA.RatingServiceJPA;
import sk.tuke.gamestudio.server.service.serviceJPA.ScoreServiceJPA;
import sk.tuke.gamestudio.server.service.serviceSORM.CommentServiceSORM;
import sk.tuke.gamestudio.server.service.serviceSORM.RatingServiceSORM;
import sk.tuke.gamestudio.server.service.serviceSORM.ScoreServiceSORM;

@Configuration
@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
		

//		SpringApplication.run(Main.class, args);
		new SpringApplicationBuilder(Main.class).web(false).run(args);

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
	
//	@Bean
//	public CommandLineRunner runner(ReplayConsoleUI ui){
//		return args ->ui.play(idGamePlay);
//	}

//	public CommandLineRunner runner(ReplayConsoleUI ui) {
//		return new CommandLineRunner() {
//
//			@Override
//			public void run(String... arg0) throws Exception {
//
//				ui.play(614);
//			}
//
//		};
//	}

	@Bean
	public GameStudioUI gameStudioUI(UserInterface... ui) {
		return new GameStudioUI(ui);
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
		return new FieldMinesweeper(9, 9, 2);
	}

	@Bean
	public CommentService commentservice() {
//		return new CommentServiceJDBC();
//		return new CommentServiceSORM();
//		return new CommentServiceJPA();
		return new CommentServiceRestClient();
	}

	@Bean
	public RatingService ratingservice() {
//		return new RatingServiceJDBC();
//		return new RatingServiceSORM();
//		return new RatingServiceJPA();
		return new RatingServiceRestClient();
	}

	@Bean
	public ScoreService scoreservice() {
//		return new ScoreServiceJDBC();
//		return new ScoreServiceSORM();
//		return new ScoreServiceJPA();
		return new ScoreServiceRestClient();
	}
	
	@Bean 
	public GamePlayService gamePlayService(){
		return new GamePlayServiceJPA();
	}
	
	@Bean
	public ReplayConsoleUI replayConsoleUI() {
		return new ReplayConsoleUI();
}}
