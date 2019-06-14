package sk.myProject.gamestudio.service;

import sk.myProject.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Transactional

public class RatingServiceJPA implements RatingService {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void setRating(Rating rating) {
        List<Rating> entity = entityManager.createQuery("SELECT r.player FROM Rating  r WHERE r.player = :player AND r.game = :game")
                .setParameter("player", rating.getPlayer())
                .setParameter("game", rating.getGame()).getResultList();//execute update
        if (entity.size() == 0 || entity.get(0) == null) {
            entityManager.persist(rating);
            return;
        }
        entityManager.createQuery("UPDATE Rating  SET ratings = :ratings ,ratedon = :ratedon WHERE player = :player AND game = :game")
                .setParameter("ratedon", rating.getRatedon())
                .setParameter("player", rating.getPlayer())
                .setParameter("game", rating.getGame())
                .setParameter("ratings", rating.getRatings()).executeUpdate();

    }

    @Override
    public int getAverageRating(String gameName) throws RatingException {
        List aa = entityManager.createNamedQuery("Rating.selectToRatings")
                .setParameter("game", gameName)
                .getResultList();
        if (aa.size() == 0 || aa.get(0) == null) {
            throw new RatingException("game '" + gameName + "' does not exist");
        }


        return (int) Math.round((double) aa.get(0));

    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        return (int) entityManager.createNamedQuery("Rating.getRating")
                .setParameter("game", game).setParameter("player", player).getSingleResult();

    }
}