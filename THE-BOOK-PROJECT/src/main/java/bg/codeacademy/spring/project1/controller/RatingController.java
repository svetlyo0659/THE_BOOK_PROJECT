package bg.codeacademy.spring.project1.controller;

import bg.codeacademy.spring.project1.model.Book;
import bg.codeacademy.spring.project1.model.Rating;
import bg.codeacademy.spring.project1.model.User;
import bg.codeacademy.spring.project1.service.BookService;
import bg.codeacademy.spring.project1.service.RatingService;
import bg.codeacademy.spring.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController
{
  private final RatingService ratingService;
  private final BookService   bookService;
  private final UserService   userService;

  @Autowired
  public RatingController(RatingService ratingService, BookService bookService, UserService userService)
  {
    this.ratingService = ratingService;
    this.bookService = bookService;
    this.userService = userService;
  }

  @PostMapping()
  public ResponseEntity<Void> addRating(@RequestParam Integer bookId, @RequestBody @Valid Rating rating, Principal principal)
  {

    Optional<Book> testBook = bookService.getBook(bookId);
    Optional<User> user = userService.getUser(principal.getName());

    if (!testBook.isPresent() || !user.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    else {

      Optional<Rating> r = ratingService.findByBookIdAndUserId(bookId, user.get().getId());
      if (r.isPresent()) {
        r.get().setRating(rating.getRating());
        ratingService.addRating(r.get());
        return ResponseEntity.ok().build();
      }
      else {
        rating.setBook(testBook.get());
        rating.setUser(user.get());
        rating.setRating(rating.getRating());

        ratingService.addRating(rating);
      }

      return ResponseEntity.ok().build();
    }
  }
}








