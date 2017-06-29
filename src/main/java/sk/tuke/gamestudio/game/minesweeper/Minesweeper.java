package sk.tuke.gamestudio.game.minesweeper;

import sk.tuke.gamestudio.game.UserInterface;
import sk.tuke.gamestudio.game.minesweeper.consoleui.ConsoleUiMinesweeper;
import sk.tuke.gamestudio.game.minesweeper.core.FieldMinesweeper;

/**
 * Main application class.
 */

public class Minesweeper {
    /** User interface. */
    private UserInterface userInterface;
    private long startMillis = System.currentTimeMillis(); 
    
    private static Minesweeper instance;
    private Settings setting;
    /**
     * Constructor.
     */
  
    private Minesweeper() {
        instance=this;
    	
        
        
//        ConsoleUI.setDifficulty();
//        Field field = new Field(setting.getRowCount(), setting.getColumnCount(), setting.getMineCount());
       
//        userInterface = new ConsoleUI(field);
//        userInterface.newGameStarted();
//        setting.save();
        
    }
 
    
	
    
  

	

	public Settings getSetting() {
		return setting;
	}

	public void setSetting(Settings setting) {
		this.setting = setting;
		this.setting.save();
	}

	public static Minesweeper getInstance() {
		if(instance==null){
			return new Minesweeper();}
		return instance;
	}

	/**
     * Main method.
     * @param args arguments
     */
	// public static void main(String[] args) {
	//
	// new Minesweeper();
	// }
    
    public int getPlayingSeconds(){
    	return ((int)((System.currentTimeMillis()-startMillis)/1000));
    }
}
