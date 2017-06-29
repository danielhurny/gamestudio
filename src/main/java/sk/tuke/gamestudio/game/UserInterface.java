package sk.tuke.gamestudio.game;

import sk.tuke.gamestudio.game.minesweeper.core.FieldMinesweeper;
import sk.tuke.gamestudio.server.entity.Score;

public interface UserInterface {

	/**
	 * Starts the game.
	 * @param field field of mines and clues
	 */
	Score newGameStarted();

	/**
	 * Updates user interface - prints the field.
	 */
	void update();

}