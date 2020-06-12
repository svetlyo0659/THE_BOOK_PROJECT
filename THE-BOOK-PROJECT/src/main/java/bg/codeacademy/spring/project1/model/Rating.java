package bg.codeacademy.spring.project1.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "ratings")
public class Rating extends IdEntity
{


  @Column(columnDefinition = "integer default 0")
  @NotNull
  @Range(min = 1, max = 10)
  private Integer rating;
  @ManyToOne(targetEntity = Book.class)
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  private Book    book;

  @OneToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;


  public Rating()
  {
  }

  public Rating(Integer rating, Book book, User user)
  {
    this.rating = rating;
    this.book = book;
    this.user = user;
  }

  public Integer getRating()
  {
    return rating;
  }

  public void setRating(Integer rating)
  {
    this.rating = rating;
  }

  public void setBook(Book book)
  {
    this.book = book;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public Book getBook()
  {
    return book;
  }

  public User getUser()
  {
    return user;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Rating)) {
      return false;
    }
    Rating rating = (Rating) o;
    return getUser().getId() == (rating.getUser().getId());
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(getUser());
  }
}
