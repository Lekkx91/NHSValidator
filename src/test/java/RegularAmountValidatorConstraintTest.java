import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class RegularAmountValidatorConstraintTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testNullAmountAndNullFrequency() {
        RegularAmount regularAmount = new RegularAmount(null, null);
        Set<ConstraintViolation<RegularAmount>> constraintViolations =
                validator.validate(regularAmount);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void nullFrequencyTestWithAnAmount() {
        RegularAmount regularAmount = new RegularAmount("$200", null);
        Set<ConstraintViolation<RegularAmount>> constraintViolations =
                validator.validate(regularAmount);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenAWeekFrequencyAndAPriceThereShouldBeNoError() {
        RegularAmount regularAmount = new RegularAmount("$200", Frequency.WEEK);
        Set<ConstraintViolation<RegularAmount>> constraintViolations =
                validator.validate(regularAmount);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenAMonthFrequencyAndAPriceThereShouldBeNoError() {
        RegularAmount regularAmount = new RegularAmount("$200", Frequency.MONTH);
        Set<ConstraintViolation<RegularAmount>> constraintViolations =
                validator.validate(regularAmount);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void ifAmountIsDivisibleByFrequencyThereShouldBeNoError() {
        RegularAmount regularAmount = new RegularAmount("200", Frequency.TWO_WEEK);
        Set<ConstraintViolation<RegularAmount>> constraintViolations =
                validator.validate(regularAmount);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void ifAmountIsNotEvenlyDivisibleByFrequencyThereShouldBeNoError() {
        RegularAmount regularAmount = new RegularAmount("201", Frequency.TWO_WEEK);
        Set<ConstraintViolation<RegularAmount>> constraintViolations =
                validator.validate(regularAmount);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void ifAmountIsNotANumberThereShouldBeNoErrorForAnyFrequency() {
        RegularAmount regularAmount = new RegularAmount("$201", Frequency.FOUR_WEEK);
        Set<ConstraintViolation<RegularAmount>> constraintViolations =
                validator.validate(regularAmount);
        assertEquals(0, constraintViolations.size());
    }
}