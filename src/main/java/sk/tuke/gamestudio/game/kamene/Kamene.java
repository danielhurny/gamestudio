package sk.tuke.gamestudio.game.kamene;

import sk.tuke.gamestudio.game.kamene.consoleui.ConsoleUiKamene;

public class Kamene {
	private ConsoleUiKamene consoleui;
	private static Kamene instance;
	private long startMillis = System.currentTimeMillis();

	public static Kamene getInstance() {
		if (instance == null) {
			return new Kamene();
		}
		return instance;
	}

	public int getPlayingSeconds() {
		return ((int) ((System.currentTimeMillis() - (startMillis + 5000)) / 1000));
	}

}
