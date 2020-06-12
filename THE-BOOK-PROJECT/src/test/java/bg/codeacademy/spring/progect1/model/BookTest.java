package bg.codeacademy.spring.progect1.model;

import bg.codeacademy.spring.project1.model.Book;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BookTest
{
  private static ValidatorFactory validatorFactory;
  private static Validator        validator;

  @BeforeClass
  public static void createValidator()
  {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @AfterClass
  public static void close()
  {
    validatorFactory.close();
  }

  @Test
  public void testBookNull()
  {
    Book book = new Book();
    book.setAuthor(null);
    book.setYear(null);
    book.setTitle(null);
    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 3);
  }

  @Test
  public void testBookInvalid()
  {
    Book book = new Book();
    book.setTitle("a");
    book.setYear(-2);
    book.setAuthor("a");
    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 3);
  }
}
