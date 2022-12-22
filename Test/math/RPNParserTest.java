package math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RPNParserTest {

    public static Stream<Arguments> dataForCalculateStack() {
        return Stream.of(
                Arguments.of("3 3 +", "6.0"),
                Arguments.of("3 3 + 4 4 + * ", "48.0"),
                Arguments.of("4", "4"),
                Arguments.of("2 2 - 4.4 4.1 + * 2 -", "-2.0"));
    }

    @ParameterizedTest
    @MethodSource({"dataForCalculateStack"})
    public void calculateStack(String expression, String result) throws IllegalAccessException {
        Assertions.assertEquals(result, RPNParser.calculateStack(expression.split(" ")));
    }

    @ParameterizedTest
    @CsvSource({"7 0 /", "3 3 + 3 3 - /"})
    public void calculateStack2(String expression) {
        assertThrows(IllegalArgumentException.class,
                () -> RPNParser.calculateStack(expression.split(" ")));
    }

    public static Stream<Arguments> dataForComputeTest() {
        return Stream.of(
                Arguments.of("3", "2", "+", 5),
                Arguments.of("3", "2", "-", 1),
                Arguments.of("4", "3", "*", 12),
                Arguments.of("3", "2", "/", 1.5));
    }

    @ParameterizedTest
    @MethodSource({"dataForComputeTest"})
    public void computeTest(String firstNumber, String secondNumber, String operator, double result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method computeMethod = RPNParser.class.getDeclaredMethod("compute", String.class, String.class, String.class);
        computeMethod.setAccessible(true);

        //Then
        Assertions.assertEquals(result, computeMethod.invoke(new RPNParser(), firstNumber, secondNumber, operator), "wrong result");
    }
}