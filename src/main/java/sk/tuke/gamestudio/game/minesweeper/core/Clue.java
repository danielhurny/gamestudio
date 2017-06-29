package sk.tuke.gamestudio.game.minesweeper.core;

/**
 * Clue tile.
 */
public class Clue extends Tile {
	/** Value of the clue. */
	private final int value;

	/**
	 * Constructor.
	 * 
	 * @param value
	 *            value of the clue
	 */
	public Clue(int value) {
		this.value = value;
	}

	// vypise value
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {

		
		 return(getState()== State.OPEN ? ((((Integer) value)).toString()):super.toString()); 
//		if (getState() == State.CLOSED) {
//			return " -";
//		} else if (getState() == State.MARKED) {
//			return " M";
//		}
//		return ((((Integer) value)).toString());
//
//	}

}}
