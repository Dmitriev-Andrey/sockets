import logic.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void fib() {
        long fib = Calculator.fib(5);
        assertEquals(8, fib);
    }
}