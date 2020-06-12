package bg.codeacademy.spring.project1.service;

import bg.codeacademy.spring.project1.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService
{
  Optional<Book> getBook(Integer id);

  Book addBook(Book book);

  void removeBook(Integer id);

  Book editBook(Integer id, Book book);

  Optional<List<Book>> findAllBooks();

  Optional<List<Book>> findBookByCriteria(String title, String author);

}


