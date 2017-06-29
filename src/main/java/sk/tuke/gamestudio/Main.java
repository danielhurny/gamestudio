package sk.tuke.gamestudio;

import java.util.Formatter;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sk.tuke.gamestudio.game.minesweeper.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.minesweeper.core.Field;
import sk.tuke.gamestudio.server.service.ScoreService;

@Configuration
@SpringBootApplication
public class Main {

	int a =2;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
//		Formatter f = new Formatter();
//		f.format("Pre vyber hry stlac pozadovane cislo:\n");
//		f.format("1 Minesweeper\n");
//		f.format("2. Kamene");
//		System.out.println(f);
		

		SpringApplication.run(Main.class, args);

	}

	@Bean
	// public CommandLineRunner runner(ConsoleUI ui) { return args ->
	// ui.newGameStarted(); }

	public CommandLineRunner runner(ConsoleUI ui) {
		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				
				ui.newGameStarted();
				}

			
		};
	}

	@Bean
	public ConsoleUI consoleUI(Field field) {
		return new ConsoleUI(field);
	}

	@Bean
	public Field field() {
		return new Field(9, 9, 1);
	}

}