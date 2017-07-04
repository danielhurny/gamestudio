package sk.tuke.gamestudio.server.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import sk.tuke.gamestudio.sorm.Column;
import sk.tuke.gamestudio.sorm.Table;
@Table(name="score")
@Entity
@NamedQuery(name = "Score.selectScore", query ="select s from Score s where s.game = :game")
public class Score {
	@Column(name="id",isPrimaryKey=true)
	@Id
	@GeneratedValue
	private int id;
	@Column(name="player")
	private String player;
	@Column(name="game")
	private String game;
	@Column (name = "points")
	private int points;
	@Column (name="playedon")
	private Date date;
	
	
	
	
	public Score() {
		super();
	}


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
	
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Hrac: " + player + " Hra: " + game + " Body: " + points + " Datum: " + date;
	}
	
	
	
	

}
