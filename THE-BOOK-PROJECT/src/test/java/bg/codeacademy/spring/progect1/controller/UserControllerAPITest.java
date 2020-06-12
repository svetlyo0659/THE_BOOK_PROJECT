package bg.codeacademy.spring.progect1.controller;

import bg.codeacademy.spring.project1.Project1Application;
import bg.codeacademy.spring.project1.dto.UserRegistration;
import bg.codeacademy.spring.project1.enums.Role;
import io.restassured.RestAssured;
import net.minidev.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Project1Application.class)
@ActiveProfiles("dev")

public class UserControllerAPITest extends AbstractTestNGSpringContextTests
{
  @LocalServerPort
  int port;

  @BeforeClass(alwaysRun = true, dependsOnMethods = "springTestContextPrepareTestInstance")
  protected void setupRestAssured()
  {
    RestAssured.port = port;
  }

  @DataProvider(name = "user-provider")
  public Object[][] dataProviderMethod()
  {
    UserRegistration admin = new UserRegistration("admin", "123456", Role.ADMIN);
    UserRegistration user = new UserRegistration("user", "password", Role.USER);

    return new Object[][]{
        {admin, HttpStatus.OK},
        {user, HttpStatus.UNAUTHORIZED}
    };
  }

  @DataProvider(name = "create-user-provider")
  public Object[][] dataCreateProviderMethod()
  {
    UserRegistration admin = new UserRegistration("admin", "123456", Role.ADMIN);
    UserRegistration user = new UserRegistration("user", "password", Role.USER);

    return new Object[][]{
        {admin, HttpStatus.CREATED},
        {user, HttpStatus.UNAUTHORIZED}
    };
  }

  @Test(dataProvider = "create-user-provider")
  public void testCreateUser(UserRegistration user, HttpStatus status)
  {
    JSONObject userParams = new JSONObject();
    userParams.put("password", "test");
    userParams.put("username", "created-user");

    RestAssured
        .given()
        .auth()
        .basic(user.getUsername(), user.getPassword())
        .contentType("application/json")
        .body(userParams.toJSONString())
        .when()
        .put("/api/v1/users")
        .then()
        .assertThat()
        .statusCode(status.value());
  }

  @Test(dataProvider = "user-provider")
  public void testGetUsers(UserRegistration user, HttpStatus status)
  {
    RestAssured
        .given()
        .auth()
        .basic(user.getUsername(), user.getPassword())
        .when()
        .get("/api/v1/users")
        .then()
        .assertThat()
        .statusCode(status.value());
  }

  @Test(dataProvider = "user-provider")
  public void testDeleteUser(UserRegistration user, HttpStatus status)
  {
    JSONObject userParams = new JSONObject();
    userParams.put("password", "test");
    userParams.put("username", "deleted-user");
    userParams.put("role", "USER");

    //create user
    RestAssured
        .given()
        .auth()
        .basic(user.getUsername(), user.getPassword())
        .contentType("application/json")
        .body(userParams.toJSONString())
        .when()
        .put("/api/v1/users");
    //delete the created user
    RestAssured.given()
        .auth()
        .basic(user.getUsername(), user.getPassword())
        .pathParam("user", user.getUsername())
        .when()
        .delete("/api/v1/users/{user}")
        .then()
        .assertThat()
        .statusCode(status.value());
  }

}
