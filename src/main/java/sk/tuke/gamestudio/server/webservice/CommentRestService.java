package sk.tuke.gamestudio.server.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import sk.tuke.gamestudio.server.entity.Comment;
import sk.tuke.gamestudio.server.service.CommentException;
import sk.tuke.gamestudio.server.service.CommentService;


@Path("/comment")
public class CommentRestService {
	@Autowired
	private CommentService commentService;
	
	@POST
	@Consumes("application/json")
	public Response addComment(Comment comment) throws CommentException {
		commentService.addComment(comment);
		return Response.ok().build();
	}
	
	@GET
	@Path("/{game}")
	@Produces("application/json")
	public List<Comment> getComment(@PathParam("game") String game) throws CommentException{
		return commentService.getComments(game.toLowerCase());
	}
	

}
