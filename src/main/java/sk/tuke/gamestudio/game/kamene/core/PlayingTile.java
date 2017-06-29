package sk.tuke.gamestudio.game.kamene.core;

import java.io.Serializable;

public class PlayingTile extends Tile implements Serializable {
	private int value;

	public int getValue() {
		return value;
	}

	public PlayingTile(int i) {
		this.value = i;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {

		return ((Integer) value).toString();
	}

}
