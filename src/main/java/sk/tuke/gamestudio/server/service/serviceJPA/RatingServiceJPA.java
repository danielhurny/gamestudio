package sk.tuke.gamestudio.server.service.serviceJPA;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.service.RatingException;
import sk.tuke.gamestudio.server.service.RatingService;

@Transactional
public class RatingServiceJPA implements RatingService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void setRating(Rating rating) throws RatingException {
		try {
			Rating r = (Rating) entityManager
					.createQuery("select r from Rating r where r.game = :game and r.player = :player")
					.setParameter("game", rating.getGame()).setParameter("player", rating.getPlayer())
					.getSingleResult();
			r.setRating(rating.getRating());
		} catch (NoResultException e) {
			entityManager.persist(rating);
		}

		// TODO Auto-generated method stub

	}

	@Override
	public double getAverageRating(String game) throws RatingException {

		Object o = entityManager.createQuery("select avg(r.rating) from Rating r where r.game = :game")
				.setParameter("game", game).getSingleResult();
		return o == null ? -1 : ((Double) o);
	}

	@Override
	public int getRating(String game, String player) throws RatingException {
		// TODO Auto-generated method stub
		Object o = entityManager
				.createQuery("select avg(r.rating) from Rating r where r.game = :game and r.player = :player")
				.setParameter("game", game).setParameter("player", player).getSingleResult();
		return o == null ? -1 : ((Double) o).intValue();

	}

}
