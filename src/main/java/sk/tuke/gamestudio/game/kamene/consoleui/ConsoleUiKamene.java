package sk.tuke.gamestudio.game.kamene.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.tuke.gamestudio.game.GameState;
import sk.tuke.gamestudio.game.UserInterface;
import sk.tuke.gamestudio.game.WrongFormatException;
import sk.tuke.gamestudio.game.kamene.Kamene;
import sk.tuke.gamestudio.game.kamene.core.FieldKamene;
import sk.tuke.gamestudio.server.entity.Score;

public class ConsoleUiKamene implements UserInterface {
	private FieldKamene field;


	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public ConsoleUiKamene(FieldKamene field) {
		super();
		this.field = field;
	}

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public Score newGameStarted() {
    Score score = null;
//		this.field = field;
		do {
			update();
			processInput();
			if (field.getStatus() == GameState.SOLVED) {
				System.out.println("Vyhrali ste!");
				score=new Score(System.getProperty("user.name"), "kamene",
							500 - Kamene.getInstance().getPlayingSeconds(), getSQLCurrentDate());
				
				break;
			}
		} while (field.getStatus() == GameState.PLAYING);
		return score;
	}

	public void update() {
		System.out.println(field.toString());
		System.out.println("Hraci cas: " + Kamene.getInstance().getPlayingSeconds());

	}

	private void processInput() {
		System.out.println("Aku akciu chces vykonat? KONIEC - exit HORE - w DOLE - s VLAVO - a VPRAVO - d");

		String userInput = readLine().toLowerCase();

		try {
			handleInput(userInput);
		} catch (WrongFormatException ex) {
			System.out.println(ex.getMessage());
		}

	}

	private void handleInput(String input) throws WrongFormatException {

		Pattern pattern = Pattern.compile("(exit|[wasd]{1})");
		Matcher matcher = pattern.matcher(input);

		if (matcher.matches()) {
			if (matcher.group(1).equals("exit")) {
				field.save();
				System.out.println("Koniec hry.");

				field.setStatus(GameState.FAILED);
				
			} else {

				String s = matcher.group(1);

				field.switchTile(s);
			}
		}

	}

	public static FieldKamene setDifficulty() {

		System.out.println(
				"Pre nacitanie hry stlac L pri stlaceni lubovolnej klavesy bude automaticky generovane pole 4X4:");

		Scanner reader = new Scanner(System.in);

		String s = reader.nextLine().toUpperCase();
		if (s.equals("L")) {

			return FieldKamene.load();
		}
		return new FieldKamene(4, 4);
	}
	
	private java.sql.Date getSQLCurrentDate() {
		return new java.sql.Date(new Date().getTime());
	}

	@Override
	public String getName() {
		return "kamene";
	}

}
