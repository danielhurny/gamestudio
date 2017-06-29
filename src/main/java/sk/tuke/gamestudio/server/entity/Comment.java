package sk.tuke.gamestudio.server.entity;

import java.util.Date;

public class Comment {
	
	private String player;
	private String game;
	private String comment;
	private Date date;
	
	
	public Comment(String player, String game, String comment, Date date) {
		super();
		this.player = player;
		this.game = game;
		this.comment = comment;
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


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Comment [player=" + player + ", game=" + game + ", comment=" + comment + ", date=" + date + "]";
	}
	
	
	
	

}
