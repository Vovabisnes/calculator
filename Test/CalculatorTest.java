import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

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

            CalculatorReader reader = new CalculatorReader();
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

            CalculatorReader reader = new CalculatorReader();
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

    @ParameterizedTest
    @CsvSource({"10", "10.0"})
    public void printTheResultTest(double result) {
        try {
            //Given
            Method printTheResultTestMethod = Calculator.class.getDeclaredMethod("printTheResult", double.class);
            printTheResultTestMethod.setAccessible(true);
            PrintStream standardOut = System.out;

            //When
            String userInput = "Your result is: 10";
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            //Then
            printTheResultTestMethod.invoke(calculator, result);
            Assertions.assertEquals(userInput, outputStream.toString(), "printTheResult Method shows wrong result");
            System.setOut(standardOut);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}