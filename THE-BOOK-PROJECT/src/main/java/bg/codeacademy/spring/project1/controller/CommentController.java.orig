package bg.codeacademy.spring.project1.controller;

import bg.codeacademy.spring.project1.model.Book;
import bg.codeacademy.spring.project1.model.Comment;
import bg.codeacademy.spring.project1.model.User;
import bg.codeacademy.spring.project1.service.BookService;
import bg.codeacademy.spring.project1.service.CommentService;
import bg.codeacademy.spring.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books/{id}/comments ")
public class CommentController
{
  private final CommentService commentService;
  private final BookService bookService;
  private final UserService userService;

  @Autowired
  public CommentController(CommentService commentService,
                           BookService bookService,
                           UserService userService)
  {
    this.commentService = commentService;
    this.bookService = bookService;
    this.userService = userService;
  }

  @PostMapping()
  public ResponseEntity<Void> addComment(@PathVariable Integer bookId,
                                         @RequestBody Comment comment,
                                         @RequestParam String userName)

  {
    if ((!bookService.getBook(bookId).isPresent()) ||
        (userService.getUser(userName) != null)) {
      return ResponseEntity.notFound().build();
    }
    else {
       User u = userService.getUser(userName);
      comment.setUser(u);
      comment.setBook(bookService.getBook(bookId).get());
      commentService.addComment(comment);
      return ResponseEntity.ok().build();
    }
  }

  @GetMapping()
  public ResponseEntity<List<Comment>> getAllBookComment(@PathVariable Integer bookId)
  {
    Book b;

    if (!bookService.getBook(bookId).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    else {
        b = bookService.getBook(bookId).get();
      return ResponseEntity.ok(commentService.getAllComments(b));
    }
  }




  @DeleteMapping("{/id} ")
  public ResponseEntity<Void> removeComment(@PathVariable Integer id)
  {

    if (!commentService.getComment(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    else {
      commentService.deleteComment(id);
      return ResponseEntity.ok().build();
    }

  }


}

