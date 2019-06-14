package sk.myProject.gamestudio.service;

import sk.myProject.gamestudio.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional

public class CommentServiceJPA implements CommentService {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addComment(Comment comment) throws CommentException {
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> getComments(String gameName) throws CommentException {
        return entityManager.createNamedQuery("Comment.getComments")
                .setParameter("game", gameName)
                .getResultList();
    }
}
