package sk.tuke.gamestudio.game.minesweeper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MineCoordinate {
	@Id
	@GeneratedValue
	private int id;
	
	private int row;
    
	 @Column(name = "col")
	private int column;

	public MineCoordinate() {
		super();
	}

	public MineCoordinate(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "MineCoordinate [row=" + row + ", column=" + column + "]";
	}
	
	
}
