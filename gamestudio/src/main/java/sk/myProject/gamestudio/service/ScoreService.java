package sk.myProject.gamestudio.service;

import sk.myProject.gamestudio.entity.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score score) throws ScoreException;

    List getBestScores(String gameName);
}
