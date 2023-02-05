package math;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RPNParserTest {
    @Nested
    class calculateStackTests {
        static Stream<Arguments> dataForCalculateStackTest() {
            return Stream.of(
                    Arguments.of("3 3 +", "6.0"),
                    Arguments.of("3 3 + 4 4 + * ", "48.0"),
                    Arguments.of("4", "4"),
                    Arguments.of("2 2 - 4.4 4.1 + * 2 -", "-2.0"));
        }

        @ParameterizedTest
        @MethodSource({"dataForCalculateStackTest"})
        void should_ReturnCorrectResult_When_CorrectData(String expression, String expected) {
            String actual = RPNParser.calculateStack(expression.split(" "));
            assertEquals(expected, actual, "Result is incorrect");
        }

        @ParameterizedTest
        @CsvSource({"7 0 /", "3 3 + 3 3 - /"})
        void should_Throw_IllegalArgumentException_When_DivisionByZero(String expression) {
            assertThrows(IllegalArgumentException.class,
                    () -> RPNParser.calculateStack(expression.split(" ")),
                    "Your expression does not contain division by zero");
        }
    }
}