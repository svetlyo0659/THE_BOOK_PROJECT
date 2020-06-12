package bg.codeacademy.spring.project1.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotHtmlValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotHtml
{
  String message() default "The text contains HTML";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

