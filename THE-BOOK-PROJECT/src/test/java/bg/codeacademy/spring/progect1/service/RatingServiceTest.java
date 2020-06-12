package bg.codeacademy.spring.progect1.service;

import bg.codeacademy.spring.project1.enums.Role;
import bg.codeacademy.spring.project1.model.Book;
import bg.codeacademy.spring.project1.model.Rating;
import bg.codeacademy.spring.project1.model.User;
import bg.codeacademy.spring.project1.repository.BookRepository;
import bg.codeacademy.spring.project1.repository.RatingRepository;
import bg.codeacademy.spring.project1.repository.UserRepository;
import bg.codeacademy.spring.project1.service.RatingService;
import bg.codeacademy.spring.project1.service.RatingServiceImpl;
import org.junit.Assert;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static java.lang.Double.NaN;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class RatingServiceTest
{

  RatingRepository ratingRepositoryMock = mock(RatingRepository.class);
  BookRepository   bookRepositoryMock   = mock(BookRepository.class);
  UserRepository   userRepositoryMock   = mock(UserRepository.class);

  RatingService ratingService = new RatingServiceImpl(ratingRepositoryMock, userRepositoryMock, bookRepositoryMock);


  @Test
  public void it_should_add_rating()
  {
    Book book1 = new Book("avtor", "knijka", 1990);
    User user1 = new User("admin", "pass", Role.ADMIN, true);
    Rating rating1 = new Rating(10, book1, user1);
    ratingService.addRating(rating1);

    Mockito.verify(ratingRepositoryMock, times(1)).save(rating1);

  }

  @Test
  public void it_should_find_book_by_criteria()
  {
    Book book1 = new Book("avtor", "knijka", 1990);
    User user1 = new User("admin", "pass", Role.ADMIN, true);
    Rating rating1 = new Rating(10, book1, user1);
    Optional<Rating> optionalRating = ratingService.findByBookIdAndUserId(book1.getId(), user1.getId());

    Mockito.verify(ratingRepositoryMock, times(1)).
        findByBookIdAndUserId(book1.getId(), user1.getId());
  }

  @Test
  public void it_should_get_rating()
  {
    Book book1 = new Book("avtor", "knijka", 1990);
    User user1 = new User("admin", "pass", Role.ADMIN, true);
    Rating rating1 = new Rating(10, book1, user1);
    double rate = ratingService.getRating(book1);

    Mockito.verify(ratingRepositoryMock, times(1)).findByBook(book1);
    Assert.assertEquals(rate, NaN, 0.0);
  }

  @Test
  public void it_should_get_all_ratings()
  {
    Book book1 = new Book("avtor", "knijka", 1990);
    User user1 = new User("admin", "pass", Role.ADMIN, true);
    Rating rating1 = new Rating(10, book1, user1);
    List<Rating> list = ratingService.getAllBookRating(book1);

    Mockito.verify(ratingRepositoryMock, times(1)).findByBook(book1);
  }

  @Test
  public void it_should_delete_ratings()
  {
    Book book1 = new Book("avtor", "knijka", 1990);
    User user1 = new User("admin", "pass", Role.ADMIN, true);
    Rating rating1 = new Rating(10, book1, user1);
    ratingService.deleteRating(rating1);

    Mockito.verify(ratingRepositoryMock, times(1)).delete(rating1);
  }
}
