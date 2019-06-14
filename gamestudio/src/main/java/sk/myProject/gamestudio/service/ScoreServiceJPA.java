package sk.myProject.gamestudio.service;

import sk.myProject.gamestudio.entity.Score;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional

public class ScoreServiceJPA implements ScoreService {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addScore(Score score) {
        entityManager.persist(score);
    }

    @Override
    public List<Score> getBestScores(String gameName) {

        return entityManager.createNamedQuery("Score.selectToScores")
                .setParameter("game", gameName)
                .setMaxResults(10).getResultList();
    }
}