package bg.codeacademy.spring.project1.model;

import bg.codeacademy.spring.project1.validation.NotHtml;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends IdEntity
{
  @NotNull(message = "Provide content!")
  @NotHtml
  @Size(min = 2, max = 256, message = "Comment between 2 and 256 characters!")
  private String content;

  private LocalDateTime date = LocalDateTime.now();

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
  @ManyToOne(targetEntity = Book.class)
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  private Book book;

  public Comment()
  {
    // default constructor
  }

  public Comment(String content, User user, Book book)
  {
    this.content = content;
    this.user = user;
    this.book = book;
  }

  public String getContent()
  {
    return content;
  }

  public Comment setContent(String content)
  {
    this.content = content;
    return this;
  }

  public User getUser()
  {
    return user;
  }

  public Comment setUser(User user)
  {
    this.user = user;
    return this;
  }

  public Book getBook()
  {
    return book;
  }

  public Comment setBook(Book book)
  {
    this.book = book;
    return this;
  }

  public LocalDateTime getDate()
  {
    return date;
  }

  public Comment setDate(LocalDateTime date)
  {
    this.date = date;
    return this;
  }
}