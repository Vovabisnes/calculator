package math;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionTest {

    @Nested
    class calculateTests {
        static Stream<Arguments> dataFor_Should_CalculateCorrectResult_When_CorrectInput() {
            return Stream.of(
                    Arguments.of(new ArrayList<>(Arrays.asList("4", "*", "0", "+", "3")), 3),
                    Arguments.of(new ArrayList<>(Arrays.asList("2", "/", "4", "-", "1")), -0.5),
                    Arguments.of(new ArrayList<>(Arrays.asList("50", "/", "0.1")), 500.0),
                    Arguments.of(new ArrayList<>(List.of("4")), 4));
        }

        @ParameterizedTest
        @MethodSource({"dataFor_Should_CalculateCorrectResult_When_CorrectInput"})
        void should_CalculateCorrectResult_When_CorrectInput(ArrayList<String> expressionList, double excepted) {
            //Given
            Expression expression = new Expression(expressionList);

            //When
            double actual = expression.calculate();

            //Then
            assertEquals(actual, excepted);
        }

    }

    @Nested
    class multiplyTests {
        static Stream<Arguments> dataFor_Should_MultiplyCorrectResult_When_CorrectInput() {
            return Stream.of(
                    Arguments.of(new ArrayList<>(Arrays.asList("4", "*", "0")), 0),
                    Arguments.of(new ArrayList<>(Arrays.asList("2", "/", "4", "*", "1")), 0.5),
                    Arguments.of(new ArrayList<>(Arrays.asList("50", "/", "0.1")), 500.0));
        }

        @ParameterizedTest
        @MethodSource({"dataFor_Should_MultiplyCorrectResult_When_CorrectInput"})
        void should_MultiplyCorrectResult_When_CorrectInput(ArrayList<String> expressionList, double excepted) {
            //Given
            Expression expression = new Expression(expressionList);

            //When
            double actual = expression.multiply();

            //Then
            assertEquals(actual, excepted);
        }

    }

}