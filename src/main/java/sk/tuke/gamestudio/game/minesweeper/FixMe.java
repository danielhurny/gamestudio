package sk.tuke.gamestudio.game.minesweeper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface FixMe {
	String whatNeedsToBeDone();

}
