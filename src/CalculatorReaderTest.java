import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


class CalculatorReaderTest {
    public static CalculatorReader reader;

    @BeforeAll
    public static void prepareData() {
        reader = new CalculatorReader();
    }

/*  @Test
    public void expressionInputTest() throws IOException {
        try {
            Method method = CalculatorReader.class.getDeclaredMethod("expressionInput", null);
            method.setAccessible(true);
            String userInput = "10.10";
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(byteArrayInputStream);
            Assertions.assertEquals("10.10", method.invoke(reader));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }*/

    @ParameterizedTest
    @CsvSource({"4.3+4-3*2+4.4-0/3", "-3  + 4 +9 *3 /10", "1.1-2.33*0", "-0-3", "3.000*10"})
    public void parseLineTest(String expression) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method parseLineMethod = CalculatorReader.class.getDeclaredMethod("parseLine", String.class);
        parseLineMethod.setAccessible(true);

        //Then
        Assertions.assertNotEquals(parseLineMethod.invoke(reader, expression), null, "Your expression is correct");
    }

    @ParameterizedTest
    @CsvSource({"4.3-2.34.4", "-10+4-2*b", "3*20.5-20/0", "3+4-5*3-5**3", "+4-4*19+3", "+2", "--290*3", "4*3-5.*2", "+-*", "a+b+c"})
    public void parseLineTest2(String expression) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method parseLineMethod = CalculatorReader.class.getDeclaredMethod("parseLine", String.class);
        parseLineMethod.setAccessible(true);

        //Then
        Assertions.assertNull(parseLineMethod.invoke(reader, expression), "Your expression is correct");
    }

    @Test
    public void countSymbolsTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method countSymbolsMethod = CalculatorReader.class.getDeclaredMethod("countSymbols", ArrayList.class);
        countSymbolsMethod.setAccessible(true);
        //When
        ArrayList<String> expression = new ArrayList<>();
        expression.add("1.35");
        expression.add("+");
        expression.add("32");
        expression.add("/");
        expression.add("434");
        expression.add("-");
        expression.add("18.4");

        //Then
        Assertions.assertEquals((int) countSymbolsMethod.invoke(reader, expression), 16, "wrong amount of symbols");
    }

    @Test
    public void hasRightSymbolsOrderTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasRightSymbolsOrderMethod = CalculatorReader.class.getDeclaredMethod("hasRightSymbolsOrder", ArrayList.class);
        hasRightSymbolsOrderMethod.setAccessible(true);
        //When
        ArrayList<String> expression = new ArrayList<>();
        expression.add("1.3");
        expression.add("+");
        expression.add("3");
        expression.add("/");
        expression.add("4");
        expression.add("-");
        expression.add("8.4");

        //Then
        Assertions.assertTrue((Boolean) hasRightSymbolsOrderMethod.invoke(reader, expression), "It is not right math expression");
    }

    @Test
    public void hasRightSymbolsOrderTest2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasRightSymbolsOrderMethod = CalculatorReader.class.getDeclaredMethod("hasRightSymbolsOrder", ArrayList.class);
        hasRightSymbolsOrderMethod.setAccessible(true);
        //When
        ArrayList<String> expression = new ArrayList<>();

        //Then
        Assertions.assertFalse((Boolean) hasRightSymbolsOrderMethod.invoke(reader, expression), "expression is not empty");
    }

    @Test
    public void hasRightSymbolsOrderTest3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasRightSymbolsOrderMethod = CalculatorReader.class.getDeclaredMethod("hasRightSymbolsOrder", ArrayList.class);
        hasRightSymbolsOrderMethod.setAccessible(true);
        //When
        ArrayList<String> expression = new ArrayList<>();
        expression.add("+");
        expression.add("4");
        expression.add("-");
        expression.add("3");
        expression.add("+");
        expression.add("2");

        //Then
        Assertions.assertFalse((Boolean) hasRightSymbolsOrderMethod.invoke(reader, expression), "first symbol is wrong");
    }

    @Test
    public void hasRightSymbolsOrderTest4() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasRightSymbolsOrderMethod = CalculatorReader.class.getDeclaredMethod("hasRightSymbolsOrder", ArrayList.class);
        hasRightSymbolsOrderMethod.setAccessible(true);
        //When
        ArrayList<String> expression = new ArrayList<>();
        expression.add("4");
        expression.add("-");
        expression.add("3");
        expression.add("3");
        expression.add("-");
        expression.add("2");

        //Then
        Assertions.assertFalse((Boolean) hasRightSymbolsOrderMethod.invoke(reader, expression), "wrong character order");
    }

    @Test
    public void hasRightSymbolsOrderTest5() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasRightSymbolsOrderMethod = CalculatorReader.class.getDeclaredMethod("hasRightSymbolsOrder", ArrayList.class);
        hasRightSymbolsOrderMethod.setAccessible(true);
        //When
        ArrayList<String> expression = new ArrayList<>();
        expression.add("4");
        expression.add("-");
        expression.add("3");
        expression.add("*");
        expression.add("2");
        expression.add("/");
        expression.add("4");

        //Then
        Assertions.assertTrue((Boolean) hasRightSymbolsOrderMethod.invoke(reader, expression), "wrong character order");
    }

    @Test
    public void hasRightSymbolsOrderTest6() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasRightSymbolsOrderMethod = CalculatorReader.class.getDeclaredMethod("hasRightSymbolsOrder", ArrayList.class);
        hasRightSymbolsOrderMethod.setAccessible(true);
        //When
        ArrayList<String> expression = new ArrayList<>();
        expression.add("-");
        expression.add("4");

        //Then
        Assertions.assertTrue((Boolean) hasRightSymbolsOrderMethod.invoke(reader, expression), "wrong character order");
    }

    @Test
    public void hasRightSymbolsOrderTest7() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasRightSymbolsOrderMethod = CalculatorReader.class.getDeclaredMethod("hasRightSymbolsOrder", ArrayList.class);
        hasRightSymbolsOrderMethod.setAccessible(true);
        //When
        ArrayList<String> expression = new ArrayList<>();
        expression.add("-");
        expression.add("3");
        expression.add("*");
        expression.add("2");
        expression.add("+");
        expression.add("3");
        expression.add("/");
        expression.add("3.5");

        //Then
        Assertions.assertTrue((Boolean) hasRightSymbolsOrderMethod.invoke(reader, expression), "wrong character order");
    }

    @Test
    public void hasRightSymbolsOrderTest8() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasRightSymbolsOrderMethod = CalculatorReader.class.getDeclaredMethod("hasRightSymbolsOrder", ArrayList.class);
        hasRightSymbolsOrderMethod.setAccessible(true);
        //When
        ArrayList<String> expression = new ArrayList<>();
        expression.add("-");
        expression.add("3");
        expression.add("5");

        //Then
        Assertions.assertFalse((Boolean) hasRightSymbolsOrderMethod.invoke(reader, expression), "wrong character order");
    }

    @ParameterizedTest
    @CsvSource({"1", "1.0", "400", "800.999"})
    public void isNumberTest(String symbol) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method isNumberMethod = CalculatorReader.class.getDeclaredMethod("isNumber", String.class);
        isNumberMethod.setAccessible(true);

        Assertions.assertTrue((Boolean) isNumberMethod.invoke(reader, symbol), "it contains operators");
    }

    @ParameterizedTest
    @CsvSource({"/", "-", "*", "+"})
    public void isNumberTest2(String symbol) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method isNumberMethod = CalculatorReader.class.getDeclaredMethod("isNumber", String.class);
        isNumberMethod.setAccessible(true);

        Assertions.assertFalse((Boolean) isNumberMethod.invoke(reader, symbol), "it contains numbers");
    }

    @Test
    public void hasDivisionByZeroTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasDivisionByZeroMethod = CalculatorReader.class.getDeclaredMethod("hasDivisionByZero", ArrayList.class);
        hasDivisionByZeroMethod.setAccessible(true);

        //When
        ArrayList<String> expression = new ArrayList<>();
        expression.add("1");
        expression.add("/");
        expression.add("3");
        expression.add("/");
        expression.add("0");

        //Then
        Assertions.assertTrue((Boolean) hasDivisionByZeroMethod.invoke(reader, expression), "Array does not have division by 0");
    }

    @Test
    public void hasDivisionByZeroTest2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasDivisionByZeroMethod = CalculatorReader.class.getDeclaredMethod("hasDivisionByZero", ArrayList.class);
        hasDivisionByZeroMethod.setAccessible(true);

        //When
        ArrayList<String> expression = new ArrayList<>();
        expression.add("1");
        expression.add("/");
        expression.add("3");
        expression.add("+");
        expression.add("0");

        //Then
        Assertions.assertFalse((Boolean) hasDivisionByZeroMethod.invoke(reader, expression), "Array has division by 0");
    }

}