package sk.myProject.gamestudio.service;

import sk.myProject.gamestudio.entity.Comment;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment) throws CommentException;

    List<Comment> getComments(String game) throws CommentException;
}