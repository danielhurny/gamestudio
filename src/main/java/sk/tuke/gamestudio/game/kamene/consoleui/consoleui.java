package sk.tuke.gamestudio.game.kamene.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.tuke.gamestudio.game.kamene.Kamene;
import sk.tuke.gamestudio.game.kamene.core.Field;
import sk.tuke.gamestudio.game.kamene.core.GameStatus;
import sk.tuke.gamestudio.server.entity.Comment;
import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.CommentException;
import sk.tuke.gamestudio.server.service.CommentServiceJDBC;
import sk.tuke.gamestudio.server.service.RatingException;
import sk.tuke.gamestudio.server.service.RatingServiceJDBC;
import sk.tuke.gamestudio.server.service.ScoreException;
import sk.tuke.gamestudio.server.service.ScoreServiceJDBC;

public class consoleui {
	private Field field;
	private ScoreServiceJDBC scoreservice = new ScoreServiceJDBC();
	private CommentServiceJDBC commentservice = new CommentServiceJDBC();
	private RatingServiceJDBC ratingservice = new RatingServiceJDBC();

	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public void newGameStarted(Field field) {

		this.field = field;
		do {
			update();
			processInput();
			if (field.getStatus() == GameStatus.SOLVED) {
				System.out.println("Vyhrali ste!");
				try {
					scoreservice.addScore(new Score(System.getProperty("user.name"), "Kamene",
							500 - Kamene.getInstance().getPlayingSeconds(), new Date()));
				} catch (ScoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		} while (true);
	}

	public void update() {
		System.out.println(field.toString());
		System.out.println("Hraci cas: " + Kamene.getInstance().getPlayingSeconds());

	}

	private void processInput() {
		System.out
				.println("Aku akciu chces vykonat? NOVA HRA - new KONIEC - exit HORE - w DOLE - s VLAVO - a VPRAVO - d\n"
						+ "pre vypis komentarov -c\n pre vypis ratingu -r\n pre vypis score -b\n pre pridanie komentara - m\n"
						+ " pre pridanie ratingu -rate\n pre zistenie ratingu od konkretneho hraca - player");

		String userInput = readLine().toLowerCase();

		try {
			handleInput(userInput);
		} catch (WrongFormatException ex) {
			System.out.println(ex.getMessage());
		}

	}

	private void handleInput(String input) throws WrongFormatException {

		Pattern pattern = Pattern.compile("(exit|new|rate|player|c|b|r|m|[wasd]{1})");
		Matcher matcher = pattern.matcher(input);

		if (matcher.matches()) {
			if (matcher.group(1).equals("exit")) {
				field.save();
				System.out.println("Koniec hry.");

				System.exit(0);
			} else if (matcher.group(1).equals("new")) {

				newGameStarted(new Field(4, 4));

			} else if (matcher.group(1).equals("b")) {
				try {
					for (Score s : scoreservice.getBestScores("Kamene"))
						System.out.println(s);
				} catch (ScoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

			} else if (matcher.group(1).equals("rate")) {
				System.out.println("Zadaj rating:");
				int rate = Integer.parseInt(readLine());
				try {
					ratingservice.setRating(new Rating(System.getProperty("user.name"), "Kamene", rate, new Date()));
				} catch (RatingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}else if (matcher.group(1).equals("player")) {
				System.out.println("Meno hraca:");
				String player = readLine();
				try {
					System.out.println(ratingservice.getRating("Kamene",player));
				} catch (RatingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (matcher.group(1).equals("m")) {
				System.out.println("Zadaj koment:");
				String s = readLine();
				try {
					commentservice.addComment(new Comment(System.getProperty("user.name"), "Kamene", s, new Date()));
				} catch (CommentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (matcher.group(1).equals("r")) {
				try {
					System.out.println(ratingservice.getAverageRating("Kamene"));
				} catch (RatingException e) {
					e.printStackTrace();
				}
			} else if (matcher.group(1).equals("c")) {
				try {
					for (Comment comment : commentservice.getComments("Kamene")) {
						System.out.println(comment);
					}
				} catch (CommentException e) {
					e.printStackTrace();
				}
			} else {

				String s = matcher.group(1);

				field.switchTile(s);
			}
		}

	}

	public static Field setDifficulty() {

		System.out.println(
				"Pre nacitanie hry stlac L pri stlaceni lubovolnej klavesy bude automaticky generovane pole 4X4:");

		Scanner reader = new Scanner(System.in);

		String s = reader.nextLine().toUpperCase();
		if (s.equals("L")) {

			return Field.load();
		}
		return new Field(4, 4);
	}

}
