package sk.tuke.gamestudio.game.minesweeper.core;

import sk.tuke.gamestudio.game.minesweeper.core.Tile.State;

/**
 * Mine tile.
 */
public class Mine extends Tile {

	@Override
	public String toString() {
		if (getState() == State.OPEN) {
			return "X";
		} return super.toString();

	
}
	}
