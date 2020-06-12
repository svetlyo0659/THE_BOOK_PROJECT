package bg.codeacademy.spring.project1.dto;

import bg.codeacademy.spring.project1.validation.NotHtml;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CommentDTO
{
  @NotNull(message = "The content of the comments cannot be NULL!")
  @NotHtml
  @Size(min = 2, max = 256, message = "Content of the comments should be between 2 and 256 characters!")
  private String        content;
  private LocalDateTime time;
  @NotNull()
  @Min(2)
  private String        userName;

  public CommentDTO()
  {
  }

  public String getContent()
  {
    return content;
  }

  public CommentDTO setContent(String content)
  {
    this.content = content;
    return this;
  }

  public LocalDateTime getTime()
  {
    return time;
  }

  public CommentDTO setTime(LocalDateTime time)
  {
    this.time = time;
    return this;
  }

  public String getUserName()
  {
    return userName;
  }

  public CommentDTO setUserName(String userName)
  {
    this.userName = userName;
    return this;
  }
}