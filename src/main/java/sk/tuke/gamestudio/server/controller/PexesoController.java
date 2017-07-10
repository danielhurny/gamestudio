package sk.tuke.gamestudio.server.controller;

import java.util.Date;
import java.util.Formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import sk.tuke.gamestudio.game.GameState;
import sk.tuke.gamestudio.game.kamene.core.FieldKamene;
import sk.tuke.gamestudio.game.pexeso.core.FieldPexeso;
import sk.tuke.gamestudio.game.pexeso.core.Tile;
import sk.tuke.gamestudio.game.pexeso.core.Tile.State;
import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.CommentService;
import sk.tuke.gamestudio.server.service.RatingService;
import sk.tuke.gamestudio.server.service.ScoreException;
import sk.tuke.gamestudio.server.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PexesoController {
	private FieldPexeso field = new FieldPexeso(4, 4);

	@Autowired
	private ScoreService scoreService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private RatingService ratingService;

	@Autowired
	private UserController userController;

	private String message;

	@RequestMapping("/pexeso")
	public String pexeso(@RequestParam(name = "command", required = false) String command,
			@RequestParam(name = "row", required = false) String row,
			@RequestParam(name = "column", required = false) String column, Model model) {

		if (command != null) {
			if ("new".equals(command)) {
				field = new FieldPexeso(4, 4);
			}
		} else {

			// field.switchTile("a");
			try {
				int rowInt = Integer.parseInt(row);
				int columnInt = Integer.parseInt(column);

				field.openTile(rowInt, columnInt);
				;

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (field.getGameState() == GameState.SOLVED) {
				message = "You won!";
				field = new FieldPexeso(4, 4);
				if (userController.isLogged()) {
					try {
						scoreService.addScore(new Score(userController.getLoggedUser().getUsername(), "pexeso",
								field.getScore(), new Date()));
					} catch (ScoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}}
		model.addAttribute("pexesoController", this);
		try {
			model.addAttribute("scores", scoreService.getBestScores("pexeso"));
			model.addAttribute("comments", commentService.getComments("pexeso"));
			model.addAttribute("ratings", ratingService.getRating("minesweeper","ja"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "pexeso";

	}

	public String renderField() {

		Formatter fr = new Formatter();

		fr.format("<table class='pexeso'>\n");

		for (int r = 0; r < field.getRowCount(); r++) {
			fr.format("<tr>\n");
			for (int c = 0; c < field.getColumnCount(); c++) {
				fr.format("<td>");
				Tile tile = field.getTile(r, c);
				String image = getImageName(tile);
				// "%3s", field.getTile(r, c)
				fr.format("<a href='?row=%d&column=%d'>", r, c);
				// fr.format("<a href='?%d'>", tile.getValue());
				// fr.format(getImageName(tile));
				fr.format("<img src ='/images/stones/%ska.png'>", image);
				fr.format("</a>");
				fr.format("</td>");
			}
			fr.format("</tr>\n");

		}

		fr.format("</table>\n");

		return fr.toString();

	}

	public String getMessage() {
		return message;
	}

	private String getImageName(Tile tile) {
		if (tile.getState() == State.CLOSED) {
			return "0";
		}
		return String.valueOf(tile.getValue());
	}
	public String getPlayingSeconds() {
		return String.valueOf(field.getPlayingTime());
	}

}
