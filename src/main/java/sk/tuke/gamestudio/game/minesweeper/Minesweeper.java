package sk.tuke.gamestudio.game.minesweeper;

import sk.tuke.gamestudio.game.minesweeper.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.minesweeper.core.Field;

/**
 * Main application class.
 */
@ClassPreamble(author = "Daniel", date = "15.06.2017", reviewers = { "justMe" })
public class Minesweeper {
    /** User interface. */
    private UserInterface userInterface;
    private long startMillis = System.currentTimeMillis(); 
    private BestTimes bestTimes;
    private static Minesweeper instance;
    private Settings setting;
    /**
     * Constructor.
     */
  
    private Minesweeper() {
        instance=this;
    	
        bestTimes=new BestTimes();
        
//        ConsoleUI.setDifficulty();
//        Field field = new Field(setting.getRowCount(), setting.getColumnCount(), setting.getMineCount());
       
//        userInterface = new ConsoleUI(field);
//        userInterface.newGameStarted();
//        setting.save();
        
    }
  @FixMe(whatNeedsToBeDone = "nothing, its just exercise")
    public BestTimes getBestTimes() {
		return bestTimes;
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
