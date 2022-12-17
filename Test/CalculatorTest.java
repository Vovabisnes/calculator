import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

class CalculatorTest {
    public static Calculator calculator;

    @BeforeAll
    public static void prepareData() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @CsvSource({"'4-4', '0'", "'4', '4.0'", "'2-3*2', '-4.0", "'-4-4*3+12*1/3*4', '0", "'2/4-1', -0.5", "'5*5+5+5*5', '55.0'", "'50/0.1', '500.0'"})
    public void calculateTest(String expression, double result) {
        try {
            //Given
            Method calculateMethod = Calculator.class.getDeclaredMethod("calculate", ArrayList.class);
            calculateMethod.setAccessible(true);

            CalculatorReader reader = new CalculatorReader(new Scanner(System.in));
            Method parseLineMethod = CalculatorReader.class.getDeclaredMethod("parseLine", String.class);
            parseLineMethod.setAccessible(true);

            //Then
            Assertions.assertEquals(result, calculateMethod.invoke(new Calculator(), parseLineMethod.invoke(reader, expression)), "wrong result");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @CsvSource({"'4-4+3', '4.0'", "'4', '4.0'", "'2-3*2', '2'", "'3*3/3*3/3', '3.0", "'2/4-1', 0.5", "'5*5+5+5*5', '25.0'", "'50/0.1-3', '500.0'"})
    public void multiplyTest(String expression, double result) {
        try {
            //Given
            Method multiplyTestMethod = Calculator.class.getDeclaredMethod("multiply", ArrayList.class);
            multiplyTestMethod.setAccessible(true);

            CalculatorReader reader = new CalculatorReader(new Scanner(System.in));
            Method parseLineMethod = CalculatorReader.class.getDeclaredMethod("parseLine", String.class);
            parseLineMethod.setAccessible(true);

            //Then
            Assertions.assertEquals(result, multiplyTestMethod.invoke(new Calculator(), parseLineMethod.invoke(reader, expression)), "wrong result");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<Arguments> dataForPrintTheResultTest() {
        return Stream.of(
                Arguments.of(10, "Your result is: 10"),
                Arguments.of(10.0, "Your result is: 10"),
                Arguments.of(23.3, "Your result is: 23.3")
        );
    }

    @ParameterizedTest
    @MethodSource({"dataForPrintTheResultTest"})
    public void printTheResultTest(double number, String result) {
        try {
            //Given
            Method printTheResultTestMethod = Calculator.class.getDeclaredMethod("printTheResult", double.class);
            printTheResultTestMethod.setAccessible(true);
            PrintStream standardOut = System.out;

            //When
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            //Then
            printTheResultTestMethod.invoke(calculator, number);
            Assertions.assertEquals(result, outputStream.toString(), "printTheResult Method shows wrong result");
            System.setOut(standardOut);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}