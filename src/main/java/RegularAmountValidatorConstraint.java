import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * {@link RegularAmountValidatorConstraint} Is the validator constraint used in validating {@link RegularAmount}
 * Check {@link ValidRegularAmount} Annotation to see usage.
 */
public class RegularAmountValidatorConstraint implements ConstraintValidator<ValidRegularAmount, RegularAmount> {

    @Override
    public void initialize(ValidRegularAmount constraintAnnotation) {
    }

    @Override
    public boolean isValid(RegularAmount regularAmount, ConstraintValidatorContext context) {
        Frequency frequency = regularAmount.getFrequency();
        String amount = regularAmount.getAmount();

        /*
          GIVEN any Frequency
          WHEN a blank Amount is provided
          THEN no validation error
         */
        if (amount == null || amount.isEmpty() && frequency != null) {
            return true;
        }

        /*
         * GIVEN any Amount
         * WHEN a null Frequency is provided
         * THEN no validation error
         */
        if (frequency == null) {
            return true;
        }

        /*
         * GIVEN a WEEK Frequency
         * WHEN any Amount is provided
         * THEN no validation error
         *
         * GIVEN a MONTH Frequency
           WHEN any Amount is provided
           THEN no validation error
         */
        if (frequency == Frequency.WEEK || frequency == Frequency.MONTH) {
            return true;
        }

        /*
         * GIVEN a Frequency is in the set TWO_WEEK, FOUR_WEEK, QUARTER, YEAR
         * AND an associated Number of Weeks is 2, 4, 13, 52 respectively
         * WHEN a Amount that divides by the Number of Weeks to a whole number of pence is provided
         * THEN no validation error
         *
         * GIVEN a Frequency is in the set TWO_WEEK, FOUR_WEEK, QUARTER, YEAR
         * AND an associated Number of Weeks is 2, 4, 13, 52 respectively
         * WHEN a Amount that does not divide by the Number of Weeks to a whole number of pence is provided
         * THEN a validation error is produced
         */

        try {
            float amountValue = Float.parseFloat(amount);
            float result = amountValue / frequency.frequencyValue;
            return ((int) result) == result;
        } catch (NumberFormatException error) {
            /*
             * GIVEN any Frequency
             * WHEN a non-numeric Amount is provided
             * THEN no validation error
             */
            return true;
        }
    }
}
