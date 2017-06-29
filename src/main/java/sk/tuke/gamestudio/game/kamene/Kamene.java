package sk.tuke.gamestudio.game.kamene;

import java.util.Date;

import sk.tuke.gamestudio.game.kamene.consoleui.ConsoleUiKamene;
import sk.tuke.gamestudio.game.kamene.core.FieldKamene;
import sk.tuke.gamestudio.server.entity.Comment;
import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.CommentException;
import sk.tuke.gamestudio.server.service.CommentServiceJDBC;
import sk.tuke.gamestudio.server.service.RatingException;
import sk.tuke.gamestudio.server.service.RatingServiceJDBC;
import sk.tuke.gamestudio.server.service.ScoreException;
import sk.tuke.gamestudio.server.service.ScoreServiceJDBC;

public class Kamene {
	private ConsoleUiKamene consoleui;
	private static Kamene instance;
	private long startMillis = System.currentTimeMillis();

//	private Kamene() {
//		new Thread(() -> {
//			int i = 5;
//			while (i > 0) {
//				System.out.println(i);
//				try {
//					i--;
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// I don't think you need to do anything for your particular
//					// problem
//				}
//			}
//			instance = this;
//			this.consoleui = new consoleui();
//			Field field = consoleui.setDifficulty();
//			consoleui.newGameStarted(field);
//		}
//
//		).start();
//	}

	public static Kamene getInstance() {
		if (instance == null) {
			return new Kamene();
		}
		return instance;
	}

	// bestTimes=new BestTimes();

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		
//
//		new Kamene();
//		
//
//		
////ScoreServiceJDBC sc=new ScoreServiceJDBC();
////try {for(Score s:sc.getBestScores("Kamene"))
////	System.out.println(s);
////} catch (ScoreException e) {
////	// TODO Auto-generated catch block
////	e.printStackTrace();
//	
//	
//}
//
////CommentServiceJDBC cs=new CommentServiceJDBC();
////try {
////	cs.addComment(new Comment("Marek", "Kamene", "Skvela hra", new Date()));
////} catch (CommentException e) {
////	// TODO Auto-generated catch block
////	e.printStackTrace();
////}
////try 
//
////RatingServiceJDBC rs= new RatingServiceJDBC();
////try {
////	rs.setRating(new Rating("Marek", "Kamene", 5, new Date()));
////} catch (RatingException e) {
////	// TODO Auto-generated catch block
////	e.printStackTrace();
////}
////try {
////	System.out.println(rs.getAverageRating("Kamene"));
////} catch (RatingException e) {
////	// TODO Auto-generated catch block
////	e.printStackTrace();
////}
////
////try {
////	System.out.println(rs.getRating("Kamene", "Marek"));
////} catch (RatingException e) {
////	// TODO Auto-generated catch block
////	e.printStackTrace();
////}
//
//	
//
//	public int getPlayingSeconds() {
//		return ((int) ((System.currentTimeMillis() - (startMillis + 5000)) / 1000));
//	}

	public int getPlayingSeconds() {
		return ((int) ((System.currentTimeMillis() - (startMillis + 5000)) / 1000));
	}	
	
}
