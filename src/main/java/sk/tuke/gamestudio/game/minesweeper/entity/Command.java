package sk.tuke.gamestudio.game.minesweeper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Command {
	@Id
	@GeneratedValue
	private int id;
	
	private CommandType type;

	private int row;

    @Column(name = "col")	
	private int column;
	
	public Command(CommandType type, int row, int column) {
		super();
		this.type = type;
		this.row = row;
		this.column = column;
	}

	public Command() {
		super();
	}
	
	

	public CommandType getType() {
		return type;
	}

	public void setType(CommandType type) {
		this.type = type;
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
		return "Command [type=" + type + ", row=" + row + ", column=" + column + "]";
	}



	
}
