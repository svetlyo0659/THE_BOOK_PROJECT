package bg.codeacademy.spring.project1.repository;

import bg.codeacademy.spring.project1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends JpaRepository<Book, Integer>
{

  Optional<List<Book>> findByTitleContainingOrAuthorContaining(String title, String author);
}
