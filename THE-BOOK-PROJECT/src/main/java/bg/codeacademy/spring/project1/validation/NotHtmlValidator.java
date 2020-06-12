package bg.codeacademy.spring.project1.validation;

import bg.codeacademy.spring.project1.util.DetectHtml;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotHtmlValidator implements ConstraintValidator<NotHtml, String>
{

  @Override
  public void initialize(NotHtml constraintAnnotation)
  {
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
  {
    if (s == null || s.isEmpty()) {
      return false;
    }
    return !DetectHtml.isHtml(s);
  }
}
