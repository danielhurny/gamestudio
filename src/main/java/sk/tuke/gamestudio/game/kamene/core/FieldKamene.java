package sk.tuke.gamestudio.game.kamene.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Formatter;
import java.util.Random;

import sk.tuke.gamestudio.game.GameState;

public class FieldKamene implements Serializable {

	private final Tile[][] tiles;

	private final int rowCount;

	private final int columnCount;

	private static final String SETTING_FILE = "Field.load";

	private int r; // suradnice empty tily
	private int c;

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	private GameState status = GameState.PLAYING;
	
	

	public void setStatus(GameState status) {
		this.status = status;
	}

	public FieldKamene(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new Tile[rowCount][columnCount];

		// generate the field content
		generate();
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public GameState getStatus() {
		return status;
	}

	public void switchTile(String s) {
		if (s.equals("w") && r - 1 >= 0) {
			Tile swap = tiles[r - 1][c];
			tiles[r - 1][c] = tiles[r][c];
			tiles[r][c] = swap;
			setR(r -= 1);

		} else if (s.equals("a") && c - 1 >= 0) {
			Tile swap = tiles[r][c - 1];
			tiles[r][c - 1] = tiles[r][c];
			tiles[r][c] = swap;
			setC(c -= 1);

		} else if (s.equals("s") && r + 1 < getRowCount()) {
			Tile swap = tiles[r + 1][c];
			tiles[r + 1][c] = tiles[r][c];
			tiles[r][c] = swap;
			setR(r += 1);
		} else if (s.equals("d") && c + 1 < getColumnCount()) {
			Tile swap = tiles[r][c + 1];
			tiles[r][c + 1] = tiles[r][c];
			tiles[r][c] = swap;
			setC(c += 1);
		}
		if (isSolved()) {
			status = GameState.SOLVED;
			return;
		}
	}

	private boolean isSolved() {
		boolean isTrue = true;

		for (int a = 0; a < rowCount; a++) {
			for (int b = 0; b < columnCount - 1; b++) {
				if (getTile(a, b).getValue() > getTile(a, b + 1).getValue()) {
					isTrue = false;
				}
			}

		}
		return isTrue;
	}

	public void generate() {

		Random random = new Random();
		int i = 1;
		while (i < rowCount * columnCount) {
			int a = random.nextInt(rowCount);
			int b = random.nextInt(columnCount);

			if (tiles[a][b] == null) {
				tiles[a][b] = new PlayingTile(i);
				i++;
			}
		}
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				if (tiles[r][c] == null) {
					tiles[r][c] = new EmptyTile(r, c);
					setR(r);
					setC(c);
				}
			}
		}

	}

	public String toString() {

		Formatter field = new Formatter();
		field.format("\n");
		for (int r = 0; r < getRowCount(); r++) {
			for (int c = 0; c < getColumnCount(); c++) {
				field.format("%3s", getTile(r, c));

			}
			field.format("\n" + "\n");
		}
		return field.toString();
	}

	public void save() {
		try (FileOutputStream fileOut = new FileOutputStream(SETTING_FILE);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(this);

		} catch (IOException e) {
			System.out.println("Nepodarilo sa zapisat");
			// TODO: handle exception
		}
	}

	public static FieldKamene load() {
		FieldKamene s = null;
		try (FileInputStream fileIn = new FileInputStream(SETTING_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			s = (FieldKamene) in.readObject();
		} catch (IOException e) {
			System.out.println("Nepodarilo sa nacitat subor");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		if (s == null) {
			return new FieldKamene(4, 4);
		} else
			return s;

	}

}
