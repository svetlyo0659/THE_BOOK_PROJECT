package bg.codeacademy.spring.project1.repository;

import bg.codeacademy.spring.project1.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "comments", path = "comments")
public interface CommentRepository extends JpaRepository<Comment, Integer>
{
  void deleteById(Integer commentId);

  List<Comment> findAllByBookId(Integer bookId);
}
