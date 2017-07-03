package sk.tuke.gamestudio.server.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import sk.tuke.gamestudio.server.entity.Comment;

public class CommentServiceJDBC implements CommentService {

	DatabaseSettings ds = new DatabaseSettings();
	
	public static final String INSERT_QUERY = "INSERT INTO comment (player,game,comment,commentedon) "
			+ "VALUES (?, ?,?,?)";
	public static final String GET_COMMENT_QUERY = "SELECT player,game,comment,commentedon FROM comment WHERE game = ?";

	@Override
	public void addComment(Comment comment) throws CommentException {

		try (Connection con = DriverManager.getConnection(ds.URL, ds.USER,
				ds.PASSWORD); PreparedStatement stm = con.prepareStatement(INSERT_QUERY);) {
			stm.setString(1, comment.getPlayer());
			stm.setString(2, comment.getGame());
			stm.setString(3, comment.getComment());
			stm.setTimestamp(4, new Timestamp(comment.getDate().getTime()));
			stm.executeUpdate();

		} catch (SQLException e) {
			throw new CommentException("Pridanie commentu neprebehlo");
		}
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comment> getComments(String game) throws CommentException {
		List<Comment> comments = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(ds.URL, ds.USER,
				ds.PASSWORD); PreparedStatement stm = con.prepareStatement(GET_COMMENT_QUERY);) {
			stm.setString(1, game);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Comment com = new Comment(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4));
				comments.add(com);
			}

		} catch (SQLException e) {
			throw new CommentException("Vyber komentarov neprebehol");
			// TODO: handle exception
		}

		// TODO Auto-generated method stub
		return comments;
	}

}
