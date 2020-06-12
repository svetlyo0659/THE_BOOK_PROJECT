package bg.codeacademy.spring.project1.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO Data transfer object is an object that carries data between processes.
 * with getters and setters
 * No use for all the fields of the model object
 */
public class BookDTO
{

  @NotNull(message = "Id cannot be NULL!")
  private Integer id;
  @NotNull(message = "Provide author's name!")
  @Size(min = 1, max = 40, message = "The author's name - between 1 and 40 characters!")
  private String  author;
  @NotNull(message = "Provide title!")
  @Size(min = 1, max = 60, message = "Title between 1 and 60 characters")
  private String  title;
  @NotNull(message = "Provide year!")
  private Integer year;
  @NotNull
  private Integer countComments;
  @Range(min = 1, max = 10)
  private Double  rating;

  public BookDTO()
  {
    // default constructor
  }

  public Integer getId()
  {
    return id;
  }

  public BookDTO setId(Integer id)
  {
    this.id = id;
    return this;
  }

  public String getAuthor()
  {
    return author;
  }

  public BookDTO setAuthor(String author)
  {
    this.author = author;
    return this;
  }

  public String getTitle()
  {
    return title;
  }

  public BookDTO setTitle(String title)
  {
    this.title = title;
    return this;
  }

  public Integer getYear()
  {
    return year;
  }

  public BookDTO setYear(Integer year)
  {
    this.year = year;
    return this;
  }

  public Integer getCountComments()
  {
    return countComments;
  }

  public BookDTO setCountComments(Integer countComments)
  {
    this.countComments = countComments;
    return this;
  }

  public Double getRating()
  {
    return rating;
  }

  public BookDTO setRating(Double rating)
  {
    this.rating = rating;
    return this;
  }
}