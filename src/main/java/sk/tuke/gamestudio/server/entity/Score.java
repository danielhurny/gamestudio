package sk.tuke.gamestudio.server.entity;

import java.util.Date;

public class Score {
	
	private String player;
	private String game;
	private int points;
	private Date date;
	
	
	public Score(String player, String game, int points, Date date) {
		super();
		this.player = player;
		this.game = game;
		this.points = points;
		this.date = date;
	}


	public String getPlayer() {
		return player;
	}


	public void setPlayer(String player) {
		this.player = player;
	}


	public String getGame() {
		return game;
	}


	public void setGame(String game) {
		this.game = game;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Score [player=" + player + ", game=" + game + ", points=" + points + ", date=" + date + "]";
	}
	
	
	
	

}
