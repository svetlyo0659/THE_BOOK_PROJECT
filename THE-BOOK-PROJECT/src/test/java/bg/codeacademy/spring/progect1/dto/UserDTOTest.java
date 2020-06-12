package bg.codeacademy.spring.progect1.dto;

import bg.codeacademy.spring.project1.dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class UserDTOTest
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
  public void testUser()
  {
    UserDTO user = new UserDTO();
    //Annotation ensures whether username is not null or an empty string
    user.setUsername("");

    Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 1);

  }
}
