package bg.codeacademy.spring.progect1.model;

import bg.codeacademy.spring.project1.model.Comment;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class CommentTest
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
  public void testCommentNull()
  {
    Comment comment = new Comment();
    comment.setContent(null);
    Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 1);
  }

  @Test
  public void testCommentInvalid()
  {
    Comment comment = new Comment();
    comment.setContent("a");

    Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 1);
  }
}
