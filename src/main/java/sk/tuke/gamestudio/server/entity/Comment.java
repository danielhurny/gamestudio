package sk.tuke.gamestudio.server.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import sk.tuke.gamestudio.sorm.Column;
import sk.tuke.gamestudio.sorm.Table;

@Table(name="comment")
@Entity
public class Comment {
	@Column(name="id",isPrimaryKey=true)
	@Id
	@GeneratedValue
	private int id;
	@Column(name="player")
	private String player;
	@Column(name="game")
	private String game;
	@Column(name="comment")
	private String comment;
	@Column(name="commentedon")
	private Date date;
	
	
	
	public Comment() {
		super();
	}


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
		return "Hrac: " + player + " Hra: " + game + " Komentar: " + comment + " Datum: " + date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
