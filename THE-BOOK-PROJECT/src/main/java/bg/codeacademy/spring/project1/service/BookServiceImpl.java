package bg.codeacademy.spring.project1.service;

import bg.codeacademy.spring.project1.model.Book;
import bg.codeacademy.spring.project1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService
{
  private final BookRepository bookRepository;

  @Autowired
  public BookServiceImpl(BookRepository bookRepository)
  {
    this.bookRepository = bookRepository;
  }

  @Override
  public Optional<Book> getBook(@NotNull Integer id)
  {
    return bookRepository.findById(id);
  }

  @Override
  public Book addBook(Book book)
  {
    return bookRepository.save(book);
  }

  @Override
  public void removeBook(Integer id)
  {
    bookRepository.deleteById(id);
  }

  @Override
  public Book editBook(Integer id, Book book)
  {
    Book b = bookRepository.getOne(id);
    b.setAuthor(book.getAuthor());
    b.setTitle(book.getTitle());
    b.setYear(book.getYear());
    return b;
  }

  @Override
  public Optional<List<Book>> findAllBooks()
  {
    Optional<List<Book>> p = Optional.of(bookRepository.findAll());
    return p;
  }

  @Override
  public Optional<List<Book>> findBookByCriteria(String title, String author)
  {
    return bookRepository.findByTitleContainingOrAuthorContaining(title, author);
  }
}