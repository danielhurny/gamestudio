package sk.tuke.gamestudio.game.minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.tuke.gamestudio.game.GameState;
import sk.tuke.gamestudio.game.UserInterface;
import sk.tuke.gamestudio.game.WrongFormatException;
import sk.tuke.gamestudio.game.minesweeper.Minesweeper;
import sk.tuke.gamestudio.game.minesweeper.core.FieldMinesweeper;
import sk.tuke.gamestudio.server.entity.Score;

/**
 * Console user interface.
 */
public class ConsoleUiMinesweeper implements UserInterface {
	/** Playing field. */
	private FieldMinesweeper field;
	// private boolean actionPerformed = false;
	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public ConsoleUiMinesweeper(FieldMinesweeper field) {
		super();
		this.field = field;
	}

	// public static void setDifficulty() {

	// System.out
	// .println("Set level: \n BEGINER - A \n INTERMEDIATE - B \n EXPERT - C \n
	// LAST SETTINGS - D\n OWN - E");
	// Scanner reader = new Scanner(System.in);
	// String inputt = reader.nextLine().toLowerCase();
	// Pattern pattern = Pattern.compile("[abcde]");
	// Matcher matcher = pattern.matcher(inputt);
	// if (matcher.matches()) {
	// switch (inputt) {
	// case "a":
	// Minesweeper.getInstance().setSetting(Settings.BEGINNER);
	// break;
	// case "b":
	// Minesweeper.getInstance().setSetting(Settings.INTERMEDIATE);
	// break;
	// case "c":
	// Minesweeper.getInstance().setSetting(Settings.EXPERT);
	// break;
	// case "d":
	// Minesweeper.getInstance().setSetting(Settings.load());
	// break;
	// case "e":
	// System.out.println("Number of rows:");
	// int r = Integer.parseInt(reader.nextLine());
	// System.out.println("Number of columns:");
	// int c = Integer.parseInt(reader.nextLine());
	// System.out.println("Number of mines:");
	// int m = Integer.parseInt(reader.nextLine());
	// Minesweeper.getInstance().setSetting(new Settings(r, c, m));
	// break;
	//
	// }
	//
	// } else {
	// Minesweeper.getInstance().setSetting(Settings.BEGINNER);
	// }
	// Minesweeper.getInstance().getSetting().save();
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.
	 * Field)
	 */
	@Override
	public Score newGameStarted() {
		Score score = null;
		System.out.println("Vitaj " + System.getProperty("user.name"));

		DateFormat df = new SimpleDateFormat("hh:mm dd.MM.YYYY");
		System.out.println(df.format(new Date()));

		// this.field = field;
		do {
			update();
			processInput();
			if (field.getState() == GameState.SOLVED) {
				System.out.println("Vyhrali ste!");

				score = new Score(System.getProperty("user.name"), "Minesweeper",
						1000 - Minesweeper.getInstance().getPlayingSeconds(), getSQLCurrentDate());

			} else if (field.getState() == GameState.FAILED) {
				System.out.println("Prehrali ste!");
				break;
			}
		} while (field.getState() == GameState.PLAYING);
		return score;
	}

	@Override
	public void update() {
		System.out.println(field.toString());
		System.out.println("Ostavajuce miny: " + field.getRemainingMineCount());
		System.out.println("Hraci cas: " + Minesweeper.getInstance().getPlayingSeconds());

	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		System.out.println("Aku akciu chces vykonat? X-ukoncenie MA1-oznacenie OA1-otvorenie");

		String userInput = readLine().toUpperCase();

		try {
			handleInput(userInput);
		} catch (WrongFormatException ex) {
			System.out.println(ex.getMessage());
		}

	}

	private void handleInput(String input) throws WrongFormatException {

		Pattern pattern = Pattern.compile("(X|(M|O)([A-Z]+)([1-9][0-9]*))");
		Matcher matcher = pattern.matcher(input);

		if (matcher.matches()) {
			if (matcher.group(1).equals("X")) {
				System.out.println("Uzivatel ukoncil hru.");

				System.exit(0);
			}

			int row = matcher.group(3).charAt(0) - 65;
			int column = Integer.parseInt(matcher.group(4)) - 1;

			if (row < field.getRowCount() && column < field.getColumnCount()) {
				if (matcher.group(2).equals("M")) {
					field.markTile(row, column);
				} else {
					field.openTile(row, column);
				}

			} else
				throw new WrongFormatException("Zadan� zl� vstup");
		} else
			throw new WrongFormatException("Zadan� zl� vstup");
		// actionPerformed = true;
	}

	private java.sql.Date getSQLCurrentDate() {
		return new java.sql.Date(new Date().getTime());
	}

	@Override
	public String getName() {

		return "Minesweeper";
	}
}
