package bg.codeacademy.spring.project1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"title", "author"})
})
public class Book extends IdEntity
{
  @Column(name = "title", nullable = false)
  @NotEmpty(message = "Provide title!")
  @Size(min = 2, max = 60, message = "Title between 1 and 60 characters")
  private String  title;
  @Column(name = "author", nullable = false)
  @NotEmpty(message = "Provide author's name!")
  @Size(min = 2, max = 40, message = "The author's name - between 1 and 40 characters!")
  private String  author;
  @Column(name = "year", nullable = false)
  @NotNull(message = "Provide year!")
  @Positive(message = "Provide positive year!")
  private Integer year;


  public Book()
  {
  }

  public Book(@NotEmpty(message = "Provide title!") @Size(min = 2, max = 60, message = "Title between 1 and 60 characters") String title, @NotEmpty(message = "Provide author's name!") @Size(min = 2, max = 40, message = "The author's name - between 1 and 40 characters!") String author, @NotNull(message = "Provide year!") @Positive(message = "Provide positive year!") Integer year)
  {
    this.title = title;
    this.author = author;
    this.year = year;
  }

  public String getTitle()
  {
    return title;
  }

  public Book setTitle(String title)
  {
    this.title = title;
    return this;
  }

  public String getAuthor()
  {
    return author;
  }

  public Book setAuthor(String author)
  {
    this.author = author;
    return this;
  }

  public Integer getYear()
  {
    return year;
  }

  public Book setYear(Integer year)
  {
    this.year = year;
    return this;
  }
}