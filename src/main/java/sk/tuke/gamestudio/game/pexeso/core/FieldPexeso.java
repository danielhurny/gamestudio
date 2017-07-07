package sk.tuke.gamestudio.game.pexeso.core;

import java.util.Random;

import sk.tuke.gamestudio.game.GameState;
import sk.tuke.gamestudio.game.pexeso.core.Tile.State;

public class FieldPexeso {

	private int rowCount;
	private int columnCount;
	private Tile[][] tiles;
	private GameState gameState = GameState.PLAYING;
	private Tile firstChosen;
	private Tile secondChosen;
	private int numberOfOpened=0;
	private int numberOfChosen=0;
	
	public FieldPexeso(int rowCount, int columnCount) {
		super();
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.tiles = new Tile[rowCount][columnCount];
		generate();
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void generate() {
		Random random = new Random();
		for (int i = 1; i <= (rowCount * columnCount) / 2; i++) {
			int isTwice = 0;
			while (isTwice <= 1) {
				int randomRow = random.nextInt(rowCount);
				int randomColumn = random.nextInt(columnCount);
				if (tiles[randomRow][randomColumn] == null) {
					tiles[randomRow][randomColumn] = new Tile(i);
					isTwice++;
				}
			}
		}

	}

	public void openTile(int row, int column) {

		Tile tile = tiles[row][column];
		if (tile.getState() == State.OPENED) {
			return;
		}
		switch (numberOfChosen) {
		case 0:
			tile.setState(State.CHOSEN);
			firstChosen = tile;
			numberOfChosen++;
			System.out.println("state 0");
			break;
		case 1:
			tile.setState(State.CHOSEN);
			secondChosen = tile;
			numberOfChosen++;

			if (firstChosen.getValue() == secondChosen.getValue()) {
				firstChosen.setState(State.OPENED);
				secondChosen.setState(State.OPENED);
				numberOfOpened+=2;
				if (numberOfOpened == rowCount * columnCount) {
					gameState = GameState.SOLVED;
				} else {

				}
			}
			System.out.println("state 1");
			break;
		case 2:
			if (firstChosen.getState() == State.CHOSEN) {
				firstChosen.setState(State.CLOSED);
				secondChosen.setState(State.CLOSED);
			}
			tile.setState(State.CHOSEN);
			numberOfChosen = 1;
			firstChosen = tile;
			secondChosen = null;
			System.out.println("state 2");
		}

	};

//	private boolean isSolved() {
//		return ((getRowCount() * getColumnCount()) - getNumberOf(State.OPENED) == 0);
//	}
//
//	private int getNumberOf(Tile.State state) {
//		int number = 0;
//		for (int j = 0; j < getRowCount(); j++) {
//
//			for (int k = 0; k < getColumnCount(); k++) {
//				if (tiles[j][k].getState() == state) {
//					number++;
//				}
//			}
//
//		}
//		return number;
//	}

}
