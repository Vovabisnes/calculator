package math;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    @Nested
    class isValidTests {
        @ParameterizedTest
        @CsvSource({"3 3 + 4 4 + * ", "4 4 +", "3 4 * 4 +"})
        void should_ReturnTrue_When_RPNExpression(String input) {
            boolean expected = Validator.isValid(input.split(" "));

            assertTrue(expected, "Your input is not RPN expression");
        }

        @ParameterizedTest
        @CsvSource({"3 + 3", "+ 4 4", "4 4 4 +", "3 3 + +", "4 3 + 4 + 4 4", "4 4", "a", "b b"})
        void should_ReturnFalse_When_NotRPNExpression(String input) {
            boolean expected = Validator.isValid(input.split(" "));

            assertFalse(expected, "Your input is RPN expression");
        }
    }

    @Nested
    class isNumberTests {
        @ParameterizedTest
        @CsvSource({"3", "4.332", "0", "4234234"})
        void should_ReturnTrue_When_Number(String input) {
            boolean expected = Validator.isNumber(input);

            assertTrue(expected, "Your input is not a number");
        }

        @ParameterizedTest
        @CsvSource({"a", ".3", "3.3.3", "4f3", "4d", "03"})
        void should_ReturnFalse_When_NotNumber(String input) {
            boolean expected = Validator.isNumber(input);

            assertFalse(expected, "Your input is a number");
        }
    }

    @Nested
    class isOperatorTests {
        @ParameterizedTest
        @CsvSource({"+", "-", "*", "/"})
        void should_ReturnTrue_When_Operator(String input) {
            boolean expected = Validator.isOperator(input);

            assertTrue(expected, "Your input is not an operator");
        }

        @ParameterizedTest
        @CsvSource({"3", "4.3", ".", "#", "s", "plus", "minus", "++"})
        void should_ReturnFalse_When_NotOperator(String input) {
            boolean expected = Validator.isOperator(input);

            assertFalse(expected, "Your input is an operator");
        }
    }
}