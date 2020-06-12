package bg.codeacademy.spring.progect1.service;

import bg.codeacademy.spring.project1.model.Book;
import bg.codeacademy.spring.project1.repository.BookRepository;
import bg.codeacademy.spring.project1.service.BookService;
import bg.codeacademy.spring.project1.service.BookServiceImpl;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;


public class BookServiceImplTest
{


  BookRepository bookRepositoryMock = mock(BookRepository.class);

  BookService bookService = new BookServiceImpl(bookRepositoryMock);

  @Test
  public void it_should_save_book()
  {
    Book book1 = new Book("Title", "Author", 2010);
    Book savedBook = bookService.addBook(book1);

    Mockito.verify(bookRepositoryMock, times(1)).save(book1);

  }

  @Test
  public void it_should_remove_book()
  {
    Book book1 = new Book("Title", "Author", 2010);
    bookService.removeBook(book1.getId());

    Mockito.verify(bookRepositoryMock, times(1)).deleteById(book1.getId());
  }

  @Test
  public void it_should_get_book()
  {
    Book book1 = new Book("Title", "Author", 2010);
    Optional<Book> savedBook = bookService.getBook(book1.getId());

    Mockito.verify(bookRepositoryMock, times(1)).findById(book1.getId());
  }


  @Test
  public void should_get_all_books()
  {

    Optional<List<Book>> books = bookService.findAllBooks();

    Mockito.verify(bookRepositoryMock, times(1)).findAll();
  }

  @Test
  public void should_get_books_by_criteria()
  {
    String title = "bl";
    String author = "bla";
    Optional<List<Book>> books = bookService.findBookByCriteria(title, author);

    Mockito.verify(bookRepositoryMock, times(1)).
        findByTitleContainingOrAuthorContaining(title, author);
  }


}
