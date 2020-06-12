package bg.codeacademy.spring.project1.service;

import bg.codeacademy.spring.project1.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService
{

  Optional<Comment> getComment(Integer commentId);

  void addComment(Comment comment);

  void deleteComment(Integer commentId);

  List<Comment> getAllComments(Integer bookId);
}
