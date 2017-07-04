package sk.tuke.gamestudio.game.minesweeper.consoleui;

import org.springframework.beans.factory.annotation.Autowired;

import sk.tuke.gamestudio.game.UserInterface;
import sk.tuke.gamestudio.game.minesweeper.core.FieldMinesweeper;
import sk.tuke.gamestudio.game.minesweeper.entity.Command;
import sk.tuke.gamestudio.game.minesweeper.entity.GamePlay;
import sk.tuke.gamestudio.server.service.GamePlayService;

public class ReplayConsoleUI {
	private static final int SLEEP_MILLIS = 1000;
	
@Autowired
GamePlayService gamePlayService;
	
	public void play(int idGamePlay){
	GamePlay gamePlay = gamePlayService.load(idGamePlay)
	;
	
	FieldMinesweeper field = new FieldMinesweeper (gamePlay.getRowCount(), gamePlay.getColumnCount(), gamePlay.getMineCoordinates());

	UserInterface consoleUI = new ConsoleUiMinesweeper(field);
	
	for(Command command:gamePlay.getCommands()){
		switch(command.getType()){
		
		case OPEN:
			field.openTile(command.getRow(), command.getColumn());
			System.out.println(field);
			break;
		case MARK:
			field.markTile(command.getRow(), command.getColumn());
			System.out.println(field);
			break;
		default:
			throw new IllegalArgumentException("Not supported command type" + command.getType());
		
			
		}
		
		try {
			Thread.sleep(SLEEP_MILLIS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	}
	}
	

