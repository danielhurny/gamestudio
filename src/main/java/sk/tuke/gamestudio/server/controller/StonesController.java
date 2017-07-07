package sk.tuke.gamestudio.server.controller;

import java.util.Formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sk.tuke.gamestudio.game.kamene.core.FieldKamene;
import sk.tuke.gamestudio.game.kamene.core.Tile;
import sk.tuke.gamestudio.server.service.ScoreService;

@Controller
public class StonesController {
	
	private FieldKamene field = new FieldKamene(3, 3);

	
	
	@Autowired
	private ScoreService scoreService;
	
    private String message;
	@RequestMapping("/stones")
	public String stones(
			@RequestParam(name = "command", required = false) String command,
			@RequestParam(name = "row", required = false) String row,
			@RequestParam(name = "column", required = false) String column, Model model) {
		
		message="";
		
		if (command != null) {
			 if("new".equals(command)){
				field = new FieldKamene(3, 3);
			}
		} else {
			
//			field.switchTile("a");
			try {
				int rowInt = Integer.parseInt(row);
				int columnInt = Integer.parseInt(column);
				
				Tile tileToMove = field.getTile(rowInt, columnInt);
				field.move(tileToMove.getValue());
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		model.addAttribute("stonesController", this);
		
		return "stones";
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
				fr.format("<a href='?row=%d&column=%d'>", r, c);
//				fr.format("<a href='?%d'>", tile.getValue());
//				fr.format(getImageName(tile));
				fr.format("<img src ='/images/stones/%ska.png'>" ,image );
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


}
