package sk.tuke.gamestudio;

import java.util.Formatter;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sk.tuke.gamestudio.server.service.CommentService;
import sk.tuke.gamestudio.server.service.CommentServiceJDBC;
import sk.tuke.gamestudio.server.service.RatingService;
import sk.tuke.gamestudio.server.service.RatingServiceJDBC;
//import sk.tuke.gamestudio.game.minesweeper.consoleui.ConsoleUI;
//import sk.tuke.gamestudio.game.minesweeper.core.Field;
import sk.tuke.gamestudio.server.service.ScoreService;
import sk.tuke.gamestudio.server.service.ScoreServiceJDBC;
import sk.tuke.gamestudio.game.kamene.consoleui.ConsoleUiKamene;
import sk.tuke.gamestudio.game.kamene.core.FieldKamene;
import sk.tuke.gamestudio.game.minesweeper.consoleui.ConsoleUiMinesweeper;
import sk.tuke.gamestudio.game.minesweeper.core.FieldMinesweeper;

@Configuration
@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
		

		SpringApplication.run(Main.class, args);

	}

	@Bean
	// public CommandLineRunner runner(ConsoleUI ui) { return args ->
	// ui.newGameStarted(); }

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
	public ConsoleUiKamene consoleUiKamene(FieldKamene field) {
		return new ConsoleUiKamene(field);
	}
	@Bean
	public ConsoleUiMinesweeper consoleUiMinesweeper(FieldMinesweeper field) {
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
		return new CommentServiceJDBC();
	}

	@Bean
	public RatingService ratingservice() {
		return new RatingServiceJDBC();
	}

	@Bean
	public ScoreService scoreservice() {
		return new ScoreServiceJDBC();
	}
}
