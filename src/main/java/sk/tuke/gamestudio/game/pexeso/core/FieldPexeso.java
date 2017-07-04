package sk.tuke.gamestudio.game.pexeso.core;

import java.util.Random;

import sk.tuke.gamestudio.game.kamene.core.GameState;

public class FieldPexeso {

	private int row;
	private int column;
	private Tile[][] tiles;
	private GameState gameState = GameState.PLAYING;

	public void generate() {
		Random random = new Random();
		for(int i=0;i<row*column;i++){
		int isTwice = 0;
		while (isTwice <= 1) {
			int randomRow = random.nextInt(row);
			int randomColumn = random.nextInt(column);
			if (tiles[randomRow][randomColumn] == null) {
				tiles[randomRow][randomColumn] = new Tile(i);
				isTwice++;
			}
		}

	}}
}
