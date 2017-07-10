package sk.tuke.gamestudio;

import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import sk.tuke.gamestudio.game.UserInterface;
import sk.tuke.gamestudio.game.WrongFormatException;
import sk.tuke.gamestudio.server.entity.Comment;
import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.CommentException;
import sk.tuke.gamestudio.server.service.CommentService;
import sk.tuke.gamestudio.server.service.RatingException;
import sk.tuke.gamestudio.server.service.RatingService;
import sk.tuke.gamestudio.server.service.ScoreException;
import sk.tuke.gamestudio.server.service.ScoreService;

public class GameStudioUI {

	@Autowired
	private ScoreService scoreservice;
	@Autowired
	private CommentService commentservice;
	@Autowired
	private RatingService ratingservice;
	
	private UserInterface[] games;

	Scanner sc = new Scanner(System.in);

	String gamePlayed = "";
	Score gamescore;

	public GameStudioUI(UserInterface[] games) {
		this.games = games;
	}

	public void play() {
//		try {
//			ratingservice.getAverageRating("Minesweeper");
//		} catch (RatingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		processInput();
		addScore(gamescore);
		while (true) {
			services();
		}

	}

	private void processInput() {
		Formatter f = new Formatter();
		f.format("Pre vyber hry stlac pozadovane cislo:\n");
		for (int i = 0; i < games.length; i++) {
			f.format("%d " + games[i].getName() + "\n", i + 1);
		}
		f.format("\npre koniec stlac 0");
		System.out.println(f);

		int userInput = Integer.parseInt(sc.nextLine());

		try {
			handleInput(userInput);
		} catch (WrongFormatException ex) {
			System.out.println(ex.getMessage());
		}

	}

	private void handleInput(int input) throws WrongFormatException {
		if (input > games.length) {
			processInput();
		} else if (input == 0) {
			System.out.println("Opustil si gamestudio");
			System.exit(0);

		} else {
			gamescore = games[input - 1].newGameStarted();
			gamePlayed += games[input - 1].getName();
		}

	}

	private void services() {


		System.out.println("Aku akciu chces vykonat?"
				+ "\n pre vyber hry -vyber\n pre vypis komentarov -c\n pre vypis ratingu -r\n pre vypis score -b\n pre pridanie komentara - m\n"
				+ " pre pridanie ratingu -rate\n pre zistenie ratingu od konkretneho hraca - player\n pre exit - exit");

		String userInput = sc.nextLine().toLowerCase();

		Pattern pattern = Pattern.compile("(vyber|exit|rate|player|c|b|r|m)");
		Matcher matcher = pattern.matcher(userInput);

		if (matcher.matches()) {
			if (matcher.group(1).equals("exit")) {

				System.out.println("Opustas game studio.");

				System.exit(0);
			} else if (matcher.group(1).equals("b")) {
				try {
					for (Score s : scoreservice.getBestScores(gamePlayed))
						System.out.println(s);
				} catch (ScoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

			}else if (matcher.group(1).equals("vyber")) {
				gamePlayed="";
				processInput();
				

			}  else if (matcher.group(1).equals("rate")) {
				System.out.println("Zadaj rating:");
				int rate = Integer.parseInt(sc.nextLine());
				try {
					ratingservice.setRating(
							new Rating(System.getProperty("user.name"), gamePlayed, rate, getSQLCurrentDate()));
				} catch (RatingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (matcher.group(1).equals("player")) {
				System.out.println("Meno hraca:");
				String player = sc.nextLine();
				try {
					System.out.println(ratingservice.getRating(gamePlayed, player));
				} catch (RatingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (matcher.group(1).equals("m")) {
				System.out.println("Zadaj koment:");
				String s = sc.nextLine();
				try {
					commentservice.addComment(
							new Comment(System.getProperty("user.name"), gamePlayed, s, getSQLCurrentDate()));
				} catch (CommentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (matcher.group(1).equals("r")) {
				try {
					System.out.println(ratingservice.getAverageRating(gamePlayed));
				} catch (RatingException e) {
					e.printStackTrace();
				}
			} else if (matcher.group(1).equals("c")) {
				try {
					for (Comment comment : commentservice.getComments(gamePlayed)) {
						System.out.println(comment);
					}
				} catch (CommentException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private java.sql.Date getSQLCurrentDate() {
		return new java.sql.Date(new Date().getTime());

	}

	private void addScore(Score score) {
		if (score == null) {
			return;
		} else {
			try {
				scoreservice.addScore(score);
			} catch (ScoreException e) {

				e.printStackTrace();
			}
		}
	}
}
