package bg.codeacademy.spring.project1.repository;

import bg.codeacademy.spring.project1.model.Book;
import bg.codeacademy.spring.project1.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "ratings", path = "ratings")
public interface RatingRepository extends JpaRepository<Rating, Integer>
{
  List<Rating> findByBook(Book book);

  Optional<Rating> findByBookIdAndUserId(Integer bookId, Integer userId);

}
