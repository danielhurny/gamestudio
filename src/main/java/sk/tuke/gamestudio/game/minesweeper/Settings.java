package sk.tuke.gamestudio.game.minesweeper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Settings implements Serializable {
	private final int rowCount;
	private final int columnCount;
	private final int mineCount;

	public static Settings BEGINNER = new Settings(9, 9, 10);
	public static Settings INTERMEDIATE = new Settings(16, 16, 40);
	public static Settings EXPERT = new Settings(16, 30, 99);

	private static final String SETTING_FILE = System.getProperty("user.home") + System.getProperty("file.separator")
			+ "minesweeper.settings";

	private static final long serialVersionUID = 1L;

	public Settings(int rowCount, int columnCount, int mineCount) {
		super();
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
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

	public static Settings load() {
		Settings s = null;
		try (FileInputStream fileIn = new FileInputStream(SETTING_FILE);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			s = (Settings) in.readObject();
		} catch (IOException e) {
			System.out.println("Nepodarilo sa nacitat");
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (s == null) {
			return Settings.BEGINNER;
		}

		return s;

	}

	@Override
	public int hashCode() {

		int result = columnCount * mineCount * rowCount;

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Settings other = (Settings) obj;
		if (columnCount != other.columnCount)
			return false;
		if (mineCount != other.mineCount)
			return false;
		if (rowCount != other.rowCount)
			return false;
		return true;
	}

}
