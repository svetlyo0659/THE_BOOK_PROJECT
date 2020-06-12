package bg.codeacademy.spring.project1.controller;

import bg.codeacademy.spring.project1.dto.BookDTO;
import bg.codeacademy.spring.project1.dto.BookDTOWithComments;
import bg.codeacademy.spring.project1.dto.CommentDTO;
import bg.codeacademy.spring.project1.model.Book;
import bg.codeacademy.spring.project1.model.Comment;
import bg.codeacademy.spring.project1.service.BookService;
import bg.codeacademy.spring.project1.service.CommentService;
import bg.codeacademy.spring.project1.service.RatingService;
import bg.codeacademy.spring.project1.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/books")
public class BookController
{

  private final BookService    bookService;
  private final RatingService  ratingService;
  private final CommentService commentService;
  private final UserService    userService;

  @Autowired
  public BookController(BookService bookService,
                        RatingService ratingService,
                        CommentService commentService,
                        UserService userService)
  {
    this.bookService = bookService;
    this.ratingService = ratingService;
    this.commentService = commentService;
    this.userService = userService;
  }


  @GetMapping("/{id}")
  public ResponseEntity<BookDTOWithComments> getBook(@PathVariable @NotNull Integer id)
  {
    Optional<Book> b = bookService.getBook(id);
    if (!b.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    else {

      BookDTOWithComments bookForClient = new BookDTOWithComments();
      List<CommentDTO> comments = new ArrayList<>();
      List<Comment> c = commentService.getAllComments(b.get().getId());
      for (Comment comment : c) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUserName(comment.getUser().getUsername())
            .setContent(comment.getContent())
            .setTime(comment.getDate());
        comments.add(commentDTO);
      }
      Book book1 = b.get();
      bookForClient.setId(book1.getId())
          .setYear(book1.getYear())
          .setAuthor(book1.getAuthor())
          .setTitle(book1.getTitle());
      bookForClient.setRating(ratingService.getRating(book1));
      bookForClient.setCommentList(comments);
      bookForClient.setCountComments(comments.size());

      return ResponseEntity.ok(bookForClient);
    }
  }


  @PostMapping()
  public ResponseEntity<Book> addBook(@RequestBody Book book)  //adding a object Book to the repo
  {

    return ResponseEntity.ok(bookService.addBook(book));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeBook(@PathVariable Integer id)
  {
    if (!bookService.getBook(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    else {
      bookService.removeBook(id);
      return ResponseEntity.ok().build();
    }
  }

  @GetMapping()
  public ResponseEntity<List<BookDTO>> findAllBooks(
      @RequestParam(required = false, defaultValue = "*") String title,
      @RequestParam(required = false, defaultValue = "*") String author)
  {

    List<BookDTO> books = new ArrayList<>();

    if ("*".equals(author) && "*".equals(title)) {
      author = title = "";
    }
    Optional<List<Book>> originBooks = bookService.findBookByCriteria(title, author);
    if (!originBooks.isPresent()) {
      return ResponseEntity.ok(books);
    }
    else {
      for (int i = 0; i < originBooks.get().size(); i++) {
        BookDTO bookDto = new BookDTO()
            .setId(originBooks.get().get(i).getId())
            .setAuthor(originBooks.get().get(i).getAuthor())
            .setTitle(originBooks.get().get(i).getTitle())
            .setYear(originBooks.get().get(i).getYear())
            .setRating(ratingService.getRating(originBooks.get().get(i)))
            .setCountComments(commentService.getAllComments(originBooks.get().get(i).getId()).size());

        books.add(bookDto);
      }
      return ResponseEntity.ok(books);
    }
  }


  @PutMapping("/{id}")
  public ResponseEntity<Book> editBook(@PathVariable Integer id, @RequestBody Book book)
  {
    Optional<Book> b = bookService.getBook(id);
    if (!b.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    else {
      Book b1 = b.get();
      b1.setAuthor(book.getAuthor());
      b1.setTitle(book.getTitle());
      b1.setYear(book.getYear());
      bookService.addBook(b1);
      return ResponseEntity.ok(b1);
    }

  }

}


