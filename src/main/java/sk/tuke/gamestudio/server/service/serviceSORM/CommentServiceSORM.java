package sk.tuke.gamestudio.server.service.serviceSORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import sk.tuke.gamestudio.server.entity.Comment;
import sk.tuke.gamestudio.server.service.CommentException;
import sk.tuke.gamestudio.server.service.CommentService;
import sk.tuke.gamestudio.server.service.DatabaseSettings;
import sk.tuke.gamestudio.sorm.SORM;

public class CommentServiceSORM implements CommentService{
	
	DatabaseSettings ds = new DatabaseSettings();
	
	@Override
	public void addComment(Comment comment) throws CommentException {
		try(Connection connection = DriverManager.getConnection(ds.URL, ds.USER, ds.PASSWORD);){
			
			SORM sorm = new SORM(connection);
			sorm.insert(comment);
			
		} catch (Exception e) {
			throw new CommentException(e.getMessage());
		} 
	}

	@Override
	public List<Comment> getComments(String game) throws CommentException {
		try(Connection connection = DriverManager.getConnection(ds.URL, ds.USER, ds.PASSWORD);){
			
			SORM sorm = new SORM(connection);
			return sorm.select(Comment.class, "where game = '" + game + "'");
			
		} catch (Exception e) {
			throw new CommentException(e.getMessage());
		}
	}

}
