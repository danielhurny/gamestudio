package sk.tuke.gamestudio.game.pexeso.core;

public class Tile {

	public enum State {
		OPENED, CLOSED, CHOSEN
	}
	
	private State state = State.CLOSED;
	private int value;
	
	public Tile(int value) {
		super();
		this.value = value;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		if(state==State.CLOSED){
			return "X";
		}else return String.valueOf(value);
		
	}
	
	

}
