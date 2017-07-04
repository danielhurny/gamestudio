package sk.tuke.gamestudio.server.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import sk.tuke.gamestudio.game.minesweeper.entity.GamePlay;

@Transactional
public class GamePlayServiceJPA implements GamePlayService {
	
	@PersistenceContext
private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see sk.tuke.gamestudio.server.service.GamePlayService#save(sk.tuke.gamestudio.game.minesweeper.entity.GamePlay)
	 */
	@Override
	public void save(GamePlay gamePlay){
		
		entityManager.persist(gamePlay);
	}
	
	/* (non-Javadoc)
	 * @see sk.tuke.gamestudio.server.service.GamePlayService#load(int)
	 */
	@Override
	public GamePlay load(int id){
		return entityManager.find(GamePlay.class, id);
		
	}
}
