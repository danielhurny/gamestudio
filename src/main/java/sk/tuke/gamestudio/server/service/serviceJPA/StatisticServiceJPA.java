package sk.tuke.gamestudio.server.service.serviceJPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import sk.tuke.gamestudio.server.dto.Statistic;
import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.service.RatingException;
import sk.tuke.gamestudio.server.service.ScoreException;
import sk.tuke.gamestudio.server.service.StatisticService;

@Transactional
public class StatisticServiceJPA implements StatisticService {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see sk.tuke.gamestudio.server.service.serviceJPA.StatisticService#getStatistic(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Statistic getStatistic(String game) throws Exception {
		// TODO Auto-generated method stub
		Statistic statistic = new Statistic();
		Object o= entityManager
				.createQuery("select MAX(s.points) from Score s where s.game = :game ")
				.setParameter("game", game).getSingleResult();
		statistic.setBestScore(o == null ? -1 :  ((Integer) o));
		System.out.println(o);
		
		o= entityManager
				.createQuery("select s.player from Score s where s.game = :game and s.points= :score ")
				.setParameter("game", game)
				.setParameter("score", statistic.getBestScore()).getResultList();
		statistic.setBestPlayers(o == null ? null :  ((List<String>) o));
		
		o= entityManager
				.createQuery("select count(s) from Score s where s.game = :game")		
				.setParameter("game", game).getSingleResult();
		statistic.setGamesPlayed(o == null ? -1 :  ((Long) o).intValue());
	
		
		
		
		return statistic;
		

	}
}
