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

import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.ScoreException;
import sk.tuke.gamestudio.server.service.ScoreService;

@Path("/score")
public class ScoreRestService {
	@Autowired
	private ScoreService scoreService;
	
	//http://localhost:8888/rest/score/hello
	
	@POST
	@Consumes("application/json")
	public Response addScore(Score score) throws ScoreException{
		scoreService.addScore(score);
		return Response.ok().build();
	}
	
	@GET
	@Path("/{game}")
	@Produces("application/json")
	public List<Score> getScores(@PathParam("game") String game) throws ScoreException{
		return scoreService.getBestScores(game);
	}

}
