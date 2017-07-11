package sk.tuke.gamestudio.server.controller;

import java.util.Date;
import java.util.Formatter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import sk.tuke.gamestudio.game.GameState;
import sk.tuke.gamestudio.game.kamene.core.FieldKamene;
import sk.tuke.gamestudio.game.kamene.core.Tile;
import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.ScoreException;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class StonesController extends GameController {

	private FieldKamene field = new FieldKamene(3, 3);

	

	@RequestMapping("/stones")
	public String stones(@RequestParam(name = "command", required = false) String command,
			@RequestParam(name = "row", required = false) String row,
			@RequestParam(name = "column", required = false) String column, Model model) {

		message = "";

		if (command != null) {
			if ("new".equals(command)) {
				field = new FieldKamene(3, 3);
			}
		} else {

			// field.switchTile("a");
			try {
				int rowInt = Integer.parseInt(row);
				int columnInt = Integer.parseInt(column);

				Tile tileToMove = field.getTile(rowInt, columnInt);
				field.move(tileToMove.getValue());

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (field.getStatus() == GameState.SOLVED) {
				message = "You won!";
				field = new FieldKamene(3, 3);
				if (userController.isLogged()) {
					try {
						scoreService.addScore(new Score(userController.getLoggedUser().getUsername(), "stones",
								field.getScore(), new Date()));
					} catch (ScoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
		
		model.addAttribute("game","stones");

		setDataToModel("stones", model);

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
				fr.format("<a href='/stones?row=%d&column=%d'>", r, c);
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

		return String.valueOf(tile.getValue());
	}
	public String getPlayingSeconds() {
		return String.valueOf(field.getPlayingTime());
	}

}
