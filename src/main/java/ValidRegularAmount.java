import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { METHOD, CONSTRUCTOR, TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = RegularAmountValidatorConstraint.class)
@Documented
public @interface ValidRegularAmount {
    String message() default Constants.INVALID_REGULAR_AMOUNT_MESSAGE;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};

}
