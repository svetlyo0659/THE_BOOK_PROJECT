package bg.codeacademy.spring.progect1.controller;

import bg.codeacademy.spring.project1.Project1Application;
import bg.codeacademy.spring.project1.model.Book;
import bg.codeacademy.spring.project1.repository.BookRepository;
import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Project1Application.class)
@ActiveProfiles("dev")
public class BookControllerTest extends AbstractTestNGSpringContextTests
{

  @LocalServerPort
  int port;

  @BeforeClass(alwaysRun = true, dependsOnMethods = "springTestContextPrepareTestInstance")
  protected void setupRestAssured()
  {
    RestAssured.port = port;
    BasicAuthScheme b = new BasicAuthScheme();
    b.setUserName("admin");
    b.setPassword("123456");
    RestAssured.authentication = b;
  }

  @Autowired
  private BookRepository bookRepository;

  public Book createBookForUsage()
  {
    Book book = new Book();
    book.setId(1);
    book.setTitle("A song of Ice and Fire");
    book.setAuthor("George R.R. Martin");
    book.setYear(1996);

    return book;
  }

  @Test
  public void add_book_request_verify_200()
  {
    JSONObject bookParams = new JSONObject();
    bookParams.put("title", "IT");
    bookParams.put("author", "Stephen King");
    bookParams.put("year", 1986);


    RestAssured.given().contentType("application/json")
        .body(bookParams.toJSONString()).when().post("/api/v1/books").
        then().assertThat().assertThat().statusCode(200).and().
        contentType(ContentType.JSON);
  }

  @Test
  public void get_all_books_request_verify_200()
  {
    RestAssured.given().when().get("/api/v1/books").then().assertThat().statusCode(200).and()
        .contentType(ContentType.JSON);
  }

  @Test
  public void get_book_request_non_valid()
  {
    RestAssured.given().when().get("/api/v1/books/99").then().assertThat().statusCode(404);
  }

  @Test
  public void add_book_request_non_valid()
  {
    JSONObject bookParams = new JSONObject();
    bookParams.put("author", "");
    bookParams.put("year", 1986);

    RestAssured.given().contentType("application/json")
        .body(bookParams.toJSONString()).when().post("/api/v1/books").
        then().assertThat().statusCode(500);

  }


  @Test
  public void add_book_that_exist_verify_error()
  {
    Book book1 = createBookForUsage();
    bookRepository.save(book1);
    book1.setId(2);

    JSONObject bookParams = new JSONObject();
    bookParams.put("author", book1.getAuthor());
    bookParams.put("title", book1.getTitle());
    bookParams.put("year", book1.getYear());

    RestAssured.given().contentType("application/json")
        .body(bookParams.toJSONString()).when().post("/api/v1/books").
        then().assertThat().statusCode(500);

    bookRepository.delete(book1);
  }

  @Test
  public void delete_book_request_non_valid()
  {
    RestAssured.given().when().delete("/api/v1/books/15").then().assertThat().statusCode(404);
  }


  @Test
  public void edit_book_request_non_valid()
  {
    JSONObject bookParams = new JSONObject();
    bookParams.put("author", "Stephen King");
    bookParams.put("title", "IT");
    bookParams.put("year", 1986);

    RestAssured.given().contentType("application/json").body(bookParams.toJSONString()).put("/api/v1/books/10")
        .then().assertThat().statusCode(404);
  }


  @Test
  public void add_book_response_verify()
  {
    JSONObject bookParams = new JSONObject();
    bookParams.put("author", "Rado");
    bookParams.put("title", "Java");
    bookParams.put("year", 2020);

    RestAssured.given().contentType("application/json").body(bookParams.toJSONString())
        .when().post("/api/v1/books").
        then().assertThat().
        contentType(ContentType.JSON).
        body("author", equalTo("Rado"))
        .body("title", equalTo("Java"))
        .body("year", equalTo(2020));

  }

}
