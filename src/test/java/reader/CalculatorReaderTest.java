package reader;

import exceptions.DivisionByZeroException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculatorReaderTest {
    private CalculatorReader calculatorReader;
    private Validator validator;
    private final Scanner scanner = new Scanner("Your expression was entered");

    @BeforeEach
    void setUp() {
        validator = mock(Validator.class);
        calculatorReader = new CalculatorReader(scanner, validator);
    }

    @Test
    void should_ReturnCorrectArrayList_When_CorrectInput() throws Exception {
        //given
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("30", "-", "20", "*", "3.3"));
        when(validator.parseLine(anyString())).thenReturn(new ArrayList<>(Arrays.asList("30", "-", "20", "*", "3.3")));

        //when
        ArrayList<String> actual = calculatorReader.startReader();

        //then
        assertEquals(expected, actual);
    }

    @Disabled
    @Test
    void should_ThrowException_When_IncorrectInput() throws Exception {
        //given
        when(validator.parseLine(anyString())).thenThrow(DivisionByZeroException.class);

        //when
        Executable executable = () -> calculatorReader.startReader();

        //then
        assertThrows(DivisionByZeroException.class, executable);
    }
}