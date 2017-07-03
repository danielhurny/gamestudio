package sk.tuke.gamestudio.server.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import sk.tuke.gamestudio.server.entity.Score;

@Transactional
public class ScoreServiceJPA implements ScoreService{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addScore(Score score) throws ScoreException {
		entityManager.persist(score);
		
	}

	@Override
	public List<Score> getBestScores(String game) throws ScoreException {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select s from Score s where s.game = :game")
				.setParameter("game", game)
				.getResultList();
	}

}
