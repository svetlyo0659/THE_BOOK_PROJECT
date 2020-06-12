package bg.codeacademy.spring.progect1.dto;

import bg.codeacademy.spring.project1.dto.BookDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BookDTOTest
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
  public void testBook()
  {
    BookDTO book = new BookDTO();
    // invalid - no ID set
    book.setAuthor("1");
    book.setYear(0);
    book.setCountComments(0);
    book.setRating(1.3);
    book.setTitle(""); //invalid - should be [1..60] symbols
    Set<ConstraintViolation<BookDTO>> violations = validator.validate(book);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 2);

  }
}