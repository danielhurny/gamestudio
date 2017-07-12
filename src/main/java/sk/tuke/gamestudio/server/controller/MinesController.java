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
import sk.tuke.gamestudio.game.minesweeper.core.Clue;
import sk.tuke.gamestudio.game.minesweeper.core.FieldMinesweeper;
import sk.tuke.gamestudio.game.minesweeper.core.Tile;
import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.CommentService;
import sk.tuke.gamestudio.server.service.RatingService;
import sk.tuke.gamestudio.server.service.ScoreException;
import sk.tuke.gamestudio.server.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)

public class MinesController extends GameController {
	private FieldMinesweeper field = new FieldMinesweeper(9, 9, 1);

	private boolean marking;


	@RequestMapping("/minesweeper")
	public String mines(@RequestParam(name = "command", required = false) String command,
			@RequestParam(name = "row", required = false) String row,
			@RequestParam(name = "column", required = false) String column, Model model) {

		message = "";

		if (command != null) {
			if ("mark".equals(command)) {
				marking = !marking;
			} else if ("new".equals(command)) {
				field = new FieldMinesweeper(9, 9, 1);

			}
		} else {

			try {
				int rowInt = Integer.parseInt(row);
				int columnInt = Integer.parseInt(column);
				if (marking) {
					field.markTile(rowInt, columnInt);
				} else {
					field.openTile(rowInt, columnInt);
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (field.getState() != GameState.PLAYING) {

				if (field.getState() == GameState.FAILED) {
					message = "You failed!";
				} else {
					message = "You won!";
					field = new FieldMinesweeper(9, 9, 1);
					if (userController.isLogged()) {
						try {
							scoreService.addScore(new Score(userController.getLoggedUser().getUsername(), "minesweeper",
									field.getScore(), new Date()));
						} catch (ScoreException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

		}
		
		model.addAttribute("game","minesweeper");
	setDataToModel("minesweeper", model);
		return "game";
	}

	public String renderField() {
		Formatter fr = new Formatter();

		fr.format("<table>\n");

		for (int r = 0; r < field.getRowCount(); r++) {
			fr.format("<tr>\n");
			for (int c = 0; c < field.getColumnCount(); c++) {
				fr.format("<td>");
				Tile tile = field.getTile(r, c);
				String image = getImageName(tile);
				// "%3s", field.getTile(r, c)
				fr.format("<a href='/minesweeper?row=%d&column=%d'>", r, c);
				fr.format("<img src ='/images/mines/%s.png'>", image);
				fr.format("</a>");
				fr.format("</td>");
			}
			fr.format("</tr>\n");

		}
		fr.format("</table>\n");

		return fr.toString();

	}

	public boolean isMarking() {
		return marking;
	}

	public String getMessage() {
		return message;
	}

	private String getImageName(Tile tile) {
		String image = null;
		switch (tile.getState()) {
		case CLOSED:
			return "closed";
		case MARKED:
			return "marked";
		case OPEN:
			if (tile instanceof Clue)
				return "open" + ((Clue) tile).getValue();
			else
				return "mine";
		}
		return null;
	}

	public String getPlayingSeconds() {
		return String.valueOf(field.getPlayingTime());
	}

}
