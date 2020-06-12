package bg.codeacademy.spring.progect1.service;

import bg.codeacademy.spring.project1.enums.Role;
import bg.codeacademy.spring.project1.model.Book;
import bg.codeacademy.spring.project1.model.Comment;
import bg.codeacademy.spring.project1.model.User;
import bg.codeacademy.spring.project1.repository.CommentRepository;
import bg.codeacademy.spring.project1.service.CommentService;
import bg.codeacademy.spring.project1.service.CommentServiceImpl;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class CommentServiceTest
{

  private CommentRepository commentRepositoryMock = mock(CommentRepository.class);

  private CommentService commentService = new CommentServiceImpl(commentRepositoryMock);


  @Test
  public void it_should_add_comment()
  {
    Book book1 = new Book("avtor", "knijka", 1990);
    User user1 = new User("admin", "pass", Role.ADMIN, true);
    Comment comment1 = new Comment("mnogo hubava kniga", user1, book1);
    commentService.addComment(comment1);

    Mockito.verify(commentRepositoryMock, times(1)).save(comment1);
  }

  @Test
  public void it_should_get_comment()
  {
    Book book1 = new Book("avtor", "knijka", 1990);
    User user1 = new User("admin", "pass", Role.USER, false);
    Comment comment1 = new Comment("mnogo hubava kniga", user1, book1);
    Optional<Comment> commentOptional = commentService.getComment(comment1.getId());

    Mockito.verify(commentRepositoryMock, times(1)).findById(comment1.getId());
  }

  @Test
  public void it_should_remove_comment()
  {
    Book book1 = new Book("avtor", "knijka", 1990);
    User user1 = new User("admin", "pass", Role.ADMIN, true);
    Comment comment1 = new Comment("mnogo hubava kniga", user1, book1);
    commentService.deleteComment(comment1.getId());

    Mockito.verify(commentRepositoryMock, times(1)).deleteById(comment1.getId());
  }

  @Test
  public void it_should_get_all_comments()
  {
    Book book1 = new Book("avtor", "knijka", 1990);
    User user1 = new User("admin", "pass", Role.USER, false);
    Comment comment1 = new Comment("mnogo hubava kniga", user1, book1);
    List<Comment> commentList = commentService.getAllComments(comment1.getBook().getId());

    Mockito.verify(commentRepositoryMock, times(1)).findAllByBookId(comment1.getBook().getId());
  }
}
