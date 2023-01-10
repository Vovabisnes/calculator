package calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reader.CalculatorReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {
    @InjectMocks
    private Calculator calculator;

    @Mock
    private CalculatorReader calculatorReader;

    @Test
    void should_CalculateCorrectResult_When_CorrectInput() {
        //given
        double expected = 10;
        when(calculatorReader.startReader()).thenReturn(List.of("20", "-", "30", "+", "20"));

        //when
        double actual = calculator.getResult();

        //then
        assertEquals(expected, actual);
    }

}