package bg.codeacademy.spring.project1.service;

import bg.codeacademy.spring.project1.model.Book;
import bg.codeacademy.spring.project1.model.Rating;
import bg.codeacademy.spring.project1.repository.BookRepository;
import bg.codeacademy.spring.project1.repository.RatingRepository;
import bg.codeacademy.spring.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService
{
  private final RatingRepository ratingRepository;
  private final UserRepository   userRepository;
  private final BookRepository   bookRepository;

  @Autowired
  public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository, BookRepository bookRepository)
  {
    this.ratingRepository = ratingRepository;
    this.userRepository = userRepository;
    this.bookRepository = bookRepository;
  }

  @Override
  public void addRating(Rating rating)
  {
    this.ratingRepository.save(rating);
  }

  public Optional<Rating> findByBookIdAndUserId(Integer bookId, Integer userId)
  {
    return ratingRepository.findByBookIdAndUserId(bookId, userId);
  }

  @Override
  public Double getRating(Book book)
  {
    List<Rating> bookRating = ratingRepository.findByBook(book);
    double az = bookRating.stream().mapToDouble(a -> a.getRating()).sum();
    double result = az / bookRating.size();
    return result;

  }

  public List<Rating> getAllBookRating(Book book)
  {


    return ratingRepository.findByBook(book);

  }


  public void deleteRating(Rating r)
  {
    ratingRepository.delete(r);
  }


}
