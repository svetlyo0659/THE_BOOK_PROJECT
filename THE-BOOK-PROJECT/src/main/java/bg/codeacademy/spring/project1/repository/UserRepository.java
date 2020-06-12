package bg.codeacademy.spring.project1.repository;

import bg.codeacademy.spring.project1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Integer>
{
  Optional<User> findUserByUsername(String userName);
}
