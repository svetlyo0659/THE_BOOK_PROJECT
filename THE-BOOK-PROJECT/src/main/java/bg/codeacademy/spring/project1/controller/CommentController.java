package bg.codeacademy.spring.project1.controller;

import bg.codeacademy.spring.project1.dto.CommentDTO;
import bg.codeacademy.spring.project1.model.Book;
import bg.codeacademy.spring.project1.model.Comment;
import bg.codeacademy.spring.project1.model.User;
import bg.codeacademy.spring.project1.service.BookService;
import bg.codeacademy.spring.project1.service.CommentService;
import bg.codeacademy.spring.project1.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/v1/books/{bookId}/comments")
public class CommentController
{
  private final CommentService commentService;
  private final BookService    bookService;
  private final UserService    userService;

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
  public ResponseEntity<Void> addComment(@RequestParam Integer bookId,
                                         @RequestBody Comment comment,
                                         Principal principal)
  {
    Optional<Book> b = bookService.getBook(bookId);
    if ((!b.isPresent())) {
      return ResponseEntity.notFound().build();
    }
    else {
      User u = userService.getUser(principal.getName()).get();

      comment.setUser(u);

      comment.setBook(b.get());
      commentService.addComment(comment);
      return ResponseEntity.ok().build();
    }
  }

  @GetMapping()
  public ResponseEntity<List<CommentDTO>> getAllBookComment(@PathVariable @NotNull Integer bookId)
  {
    if (!bookService.getBook(bookId).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    else {
      Comparator<CommentDTO> comparatorByTime = (a, b) -> b.getTime().compareTo(a.getTime());
      List<CommentDTO> sortedComments = new ArrayList<>();
      List<Comment> commentsFromDb = commentService.getAllComments(bookId);
      for (Comment c : commentsFromDb) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUserName(c.getUser().getUsername())
            .setContent(c.getContent())
            .setTime(c.getDate());
        sortedComments.add(commentDTO);
      }
      Collections.sort(sortedComments, comparatorByTime);
      return ResponseEntity.ok(sortedComments);
    }
  }


  @DeleteMapping("/{commentId}")
  public ResponseEntity<Void> removeComment(@PathVariable @NotNull Integer bookId, @PathVariable @NotNull Integer commentId)
  {
    if (!bookService.getBook(bookId).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    else {
      if (!commentService.getComment(commentId).isPresent()) {
        return ResponseEntity.notFound().build();
      }
      else {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
      }
    }

  }


}

