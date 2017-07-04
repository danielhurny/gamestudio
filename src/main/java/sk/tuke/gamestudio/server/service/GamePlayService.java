package sk.tuke.gamestudio.server.service;

import sk.tuke.gamestudio.game.minesweeper.entity.GamePlay;

public interface GamePlayService {

	void save(GamePlay gamePlay);

	GamePlay load(int id);

}