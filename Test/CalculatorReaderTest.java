import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

class CalculatorReaderTest {
    public static CalculatorReader reader;

    @BeforeAll
    public static void prepareData() {
        reader = new CalculatorReader(new Scanner(System.in));
    }

    @Test
    public void startReaderTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method startReaderMethod = CalculatorReader.class.getDeclaredMethod("startReader", null);
        startReaderMethod.setAccessible(true);

        String input = "20-30+50";
        Scanner scanner = new Scanner(input);
        CalculatorReader inputReader = new CalculatorReader(scanner);

        ArrayList<String> expressionList = new ArrayList<>(Arrays.asList("20", "-", "30", "+", "50"));

        //Then
        Assertions.assertEquals(expressionList, startReaderMethod.invoke(inputReader));
    }

/*    @Test
    public void startReaderTest2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method startReaderMethod = CalculatorReader.class.getDeclaredMethod("startReader", null);
        startReaderMethod.setAccessible(true);

        String input = "20-30+a";
        Scanner scanner = new Scanner(input);
        CalculatorReader inputReader = new CalculatorReader(scanner);

        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        //When
        System.setOut(new PrintStream(outputStream));
        startReaderMethod.invoke(inputReader);

        //Then
        Assertions.assertEquals(outputStream.toString(), "Enter your math expression\nYour expression is not correct\nEnter your math expression");
        System.setOut(standardOut);
    }*/

    @Test
    public void expressionInputTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        String input = "20-30+50";
        Scanner scanner = new Scanner(input);
        CalculatorReader inputReader = new CalculatorReader(scanner);
        Method expressionInputMethod = CalculatorReader.class.getDeclaredMethod("expressionInput", null);
        expressionInputMethod.setAccessible(true);

        //Then
        Assertions.assertEquals("20-30+50", expressionInputMethod.invoke(inputReader));
    }

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

    public static Stream<Arguments> dataForIsWrongExpression() {
        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList("1.3", "+", "/", "4", "-", "8.4"))),
                Arguments.of(new ArrayList<>(Arrays.asList("4", "4", "*", "2", "/", "4"))),
                Arguments.of(new ArrayList<>(Arrays.asList("-", "4", "+", "4", "4"))),
                Arguments.of(new ArrayList<>(Arrays.asList("-", "3", "-", "*", "+", "3", "/", "3.5"))),
                Arguments.of(new ArrayList<>(Arrays.asList("+", "4", "+", "4", "4"))),
                Arguments.of(new ArrayList<>(Arrays.asList("*", "4", "+", "4", "4"))),
                Arguments.of(new ArrayList<>(Arrays.asList("-", "4", "+", "4", "/", "0"))));
    }

    @ParameterizedTest
    @MethodSource({"dataForIsWrongExpression"})
    public void isWrongExpressionTest(ArrayList<String> expression) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method isWrongExpressionMethod = CalculatorReader.class.getDeclaredMethod("isWrongExpression", ArrayList.class);
        isWrongExpressionMethod.setAccessible(true);

        //Then
        Assertions.assertTrue((Boolean) isWrongExpressionMethod.invoke(reader, expression), "expression is correct");
    }

    public static Stream<Arguments> dataForIsRightExpression() {
        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList("1.3", "+", "4", "-", "4"))),
                Arguments.of(new ArrayList<>(Arrays.asList("-", "4", "+", "4"))),
                Arguments.of(new ArrayList<>(Arrays.asList("30.5", "-", "40.3", "*", "0"))),
                Arguments.of(new ArrayList<>(Arrays.asList("200", "*", "2.3", "/", "1"))),
                Arguments.of(new ArrayList<>(Arrays.asList("-", "4", "+", "4", "/", "1000.3333"))));
    }

    @ParameterizedTest
    @MethodSource({"dataForIsRightExpression"})
    public void isRightExpressionTest(ArrayList<String> expression) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method isWrongExpressionMethod = CalculatorReader.class.getDeclaredMethod("isWrongExpression", ArrayList.class);
        isWrongExpressionMethod.setAccessible(true);

        //Then
        Assertions.assertFalse((Boolean) isWrongExpressionMethod.invoke(reader, expression), "expression is not correct");
    }

    @Test
    public void hasDivisionByZeroTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasDivisionByZeroMethod = CalculatorReader.class.getDeclaredMethod("hasDivisionByZero", ArrayList.class);
        hasDivisionByZeroMethod.setAccessible(true);

        //When
        ArrayList<String> expression = new ArrayList<>(Arrays.asList("1", "/", "3", "/", "0"));

        //Then
        Assertions.assertTrue((Boolean) hasDivisionByZeroMethod.invoke(reader, expression), "Array does not have division by 0");
    }

    @Test
    public void hasNotDivisionByZeroTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasDivisionByZeroMethod = CalculatorReader.class.getDeclaredMethod("hasDivisionByZero", ArrayList.class);
        hasDivisionByZeroMethod.setAccessible(true);

        //When
        ArrayList<String> expression = new ArrayList<>(Arrays.asList("1", "/", "3", "+", "0"));

        //Then
        Assertions.assertFalse((Boolean) hasDivisionByZeroMethod.invoke(reader, expression), "Array has division by 0");
    }

    public static Stream<Arguments> dataForHasRightSymbolsOrderTest() {
        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList("1.3", "+", "3", "/", "4", "-", "8.4"))),
                Arguments.of(new ArrayList<>(Arrays.asList("4", "-", "3", "*", "2", "/", "4"))),
                Arguments.of(new ArrayList<>(Arrays.asList("-", "4"))),
                Arguments.of(new ArrayList<>(Arrays.asList("-", "3", "*", "2", "+", "3", "/", "3.5"))));
    }

    @ParameterizedTest
    @MethodSource({"dataForHasRightSymbolsOrderTest"})
    public void hasRightSymbolsOrderTest(ArrayList<String> expression) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasNotRightSymbolsOrderMethod = CalculatorReader.class.getDeclaredMethod("hasNotRightSymbolsOrder", ArrayList.class);
        hasNotRightSymbolsOrderMethod.setAccessible(true);

        //Then
        Assertions.assertFalse((Boolean) hasNotRightSymbolsOrderMethod.invoke(reader, expression), "expression is not correct");
    }

    public static Stream<Arguments> dataForHasNotRightSymbolsOrderTest() {
        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList("4", "-", "3", "3", "-", "2"))),
                Arguments.of(new ArrayList<>(Arrays.asList("4", "-", "-", "3", "-", "2"))),
                Arguments.of(new ArrayList<>(Arrays.asList("-", "3", "5"))),
                Arguments.of(new ArrayList<>(Arrays.asList("-", "3", "*", "*", "2", "+", "3"))));
    }

    @ParameterizedTest
    @MethodSource({"dataForHasNotRightSymbolsOrderTest"})
    public void hasNotRightSymbolsOrderTest(ArrayList<String> expression) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasNotRightSymbolsOrderMethod = CalculatorReader.class.getDeclaredMethod("hasNotRightSymbolsOrder", ArrayList.class);
        hasNotRightSymbolsOrderMethod.setAccessible(true);

        //Then
        Assertions.assertTrue((Boolean) hasNotRightSymbolsOrderMethod.invoke(reader, expression), "expression is correct");
    }

    public static Stream<Arguments> dataForHasWrongOperatorAtTheBeginning() {
        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList("+", "33"))),
                Arguments.of(new ArrayList<>(Arrays.asList("/", "33"))),
                Arguments.of(new ArrayList<>(Arrays.asList("*", "33"))),
                Arguments.of(new ArrayList<>(List.of("*"))));
    }

    @ParameterizedTest
    @MethodSource({"dataForHasWrongOperatorAtTheBeginning"})
    public void hasWrongOperatorAtTheBeginningTest(ArrayList<String> expression) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasWrongOperatorAtTheBeginningMethod = CalculatorReader.class.getDeclaredMethod("hasWrongOperatorAtTheBeginning", ArrayList.class);
        hasWrongOperatorAtTheBeginningMethod.setAccessible(true);

        //Then
        Assertions.assertTrue((Boolean) hasWrongOperatorAtTheBeginningMethod.invoke(reader, expression), "expression has an correct operator at the beginning");
    }

    public static Stream<Arguments> dataForHasNotWrongOperatorAtTheBeginning() {
        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList("-", "33"))),
                Arguments.of(new ArrayList<>(List.of("400.32"))));
    }

    @ParameterizedTest
    @MethodSource({"dataForHasNotWrongOperatorAtTheBeginning"})
    public void hasNotWrongOperatorAtTheBeginningTest(ArrayList<String> expression) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasWrongOperatorAtTheBeginningMethod = CalculatorReader.class.getDeclaredMethod("hasWrongOperatorAtTheBeginning", ArrayList.class);
        hasWrongOperatorAtTheBeginningMethod.setAccessible(true);

        //Then
        Assertions.assertFalse((Boolean) hasWrongOperatorAtTheBeginningMethod.invoke(reader, expression), "expression has wrong operator at the beginning");
    }

    public static Stream<Arguments> dataForHasWrongAmountOfSymbols() {
        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList("-", "33", "+")), "-3+"),
                Arguments.of(new ArrayList<>(Arrays.asList("2", "-")), "2-"),
                Arguments.of(new ArrayList<>(Arrays.asList("400", "-", "20", "+", "30.0", "*")), "400-20+30.0 *"));
    }

    @ParameterizedTest
    @MethodSource({"dataForHasWrongAmountOfSymbols"})
    public void hasWrongAmountOfSymbols(ArrayList<String> expression, String line) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Given
        Method hasWrongAmountOfSymbols = CalculatorReader.class.getDeclaredMethod("hasWrongAmountOfSymbols", ArrayList.class, String.class);
        hasWrongAmountOfSymbols.setAccessible(true);

        //Then
        Assertions.assertTrue((Boolean) hasWrongAmountOfSymbols.invoke(reader, expression, line), "expression is correct");
    }

    @ParameterizedTest
    @CsvSource({"1", "1.0", "400", "800.999", "0.33", "0.3355", "0"})
    public void isNumberTest(String symbol) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method isNumberMethod = CalculatorReader.class.getDeclaredMethod("isNumber", String.class);
        isNumberMethod.setAccessible(true);

        Assertions.assertTrue((Boolean) isNumberMethod.invoke(reader, symbol), "it contains operators");
    }

    @ParameterizedTest
    @CsvSource({"/", "-", "*", "+", "012", "000"})
    public void isNotNumberTest(String symbol) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method isNumberMethod = CalculatorReader.class.getDeclaredMethod("isNumber", String.class);
        isNumberMethod.setAccessible(true);

        Assertions.assertFalse((Boolean) isNumberMethod.invoke(reader, symbol), "it contains numbers");
    }
}