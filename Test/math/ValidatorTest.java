package math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidatorTest {

    @ParameterizedTest
    @CsvSource({"3 3 + 4 4 + * ", "4 4 +", "3 4 * 4 +"})
    public void isValidTest(String expression) {
        Assertions.assertTrue(Validator.isValid(expression.split(" ")));
    }

    @ParameterizedTest
    @CsvSource({"3 + 3", "+ 4 4", "4 4 4 +", "3 3 + +", "4 3 + 4 + 4 4", "4 4", "a", "b b"})
    public void isNotValidTest(String expression) {
        Assertions.assertFalse(Validator.isValid(expression.split(" ")));
    }

    @ParameterizedTest
    @CsvSource({"3", "4.3", "0", "4"})
    public void isNumberTest(String number) {
        Assertions.assertTrue(Validator.isNumber(number));
    }

    @ParameterizedTest
    @CsvSource({"a", ".3", "3.3.3", "4f3", "4d", "03"})
    public void isNotNumberTest(String number) {
        Assertions.assertFalse(Validator.isNumber(number));
    }

    @ParameterizedTest
    @CsvSource({"3", "4.3", ".", "#", "s", "plus", "minus"})
    public void isNotOperatorTest(String operator) {
        Assertions.assertFalse(Validator.isOperator(operator));
    }

    @ParameterizedTest
    @CsvSource({"+", "-", "*", "/"})
    public void isOperatorTest(String operator) {
        Assertions.assertTrue(Validator.isOperator(operator));
    }
}