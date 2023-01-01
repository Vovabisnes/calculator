package reader;

import exceptions.DivisionByZeroException;
import exceptions.WrongExpressionException;
import exceptions.WrongSymbolException;
import exceptions.WrongSymbolsOrderException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    private final Validator validator = new Validator();

    @Nested
    class parseLineTests {
        static Stream<Arguments> dataFor_Should_ReturnCorrectArrayList_When_CorrectInput() {
            return Stream.of(
                    Arguments.of("4 * 0+3", new ArrayList<>(Arrays.asList("4", "*", "0", "+", "3"))),
                    Arguments.of("2/4-1", new ArrayList<>(Arrays.asList("2", "/", "4", "-", "1"))),
                    Arguments.of("50/0.1", new ArrayList<>(Arrays.asList("50", "/", "0.1"))),
                    Arguments.of("4", new ArrayList<>(List.of("4"))));
        }

        @ParameterizedTest
        @MethodSource({"dataFor_Should_ReturnCorrectArrayList_When_CorrectInput"})
        void should_ReturnCorrectArrayList_When_CorrectInput(String line, ArrayList<String> excepted) throws Exception {
            //Given

            //When
            ArrayList<String> actual = validator.parseLine(line);

            //Then
            assertEquals(actual, excepted);
        }

        @Test
        void should_ThrowException_When_IncorrectInput() {
            assertAll(
                    () -> assertThrows(WrongSymbolException.class, () -> validator.parseLine("7/3-4a")),
                    () -> assertThrows(WrongExpressionException.class, () -> validator.parseLine("32+3 +")),
                    () -> assertThrows(WrongSymbolsOrderException.class, () -> validator.parseLine("2++3")),
                    () -> assertThrows(DivisionByZeroException.class, () -> validator.parseLine("2/3/0")));
        }
    }
}