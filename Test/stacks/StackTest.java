package stacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    private static Stack stack;

    @BeforeEach
    public void prepareData() {
        stack = new Stack(2);
    }

    @Test
    void pushTest() throws IllegalArgumentException {
        stack.push("element");
        String actual = stack.peek();
        String expected = "element";
        assertEquals(expected, actual, "nothing was pushed to stack");
    }

    @Test
    void pushTestWhenException() throws IllegalArgumentException {
        stack.push("element");
        stack.push("element2");
        assertThrows(IllegalArgumentException.class,
                () -> stack.push("element3"), "position does not out equal length of stack");
    }

    @Test
    void peekTest() throws IllegalArgumentException {
        stack.push("element");
        String actual = stack.peek();
        String expected = "element";
        assertEquals(expected, actual, "wrong element was picked");
    }

    @Test
    void peekTestWhenEmptyStack() throws IllegalArgumentException {
        assertNull(stack.peek(), "stack is not empty");
    }

    @Test
    void popTest() throws IllegalArgumentException {
        stack.push("element");
        stack.push("element2");
        assertAll(
                () -> assertEquals("element2", stack.pop(), "false element was deleted"),
                () -> assertEquals("element", stack.peek(), "the last element in stack is wrong")
        );
    }

    @Test
    void isEmptyTest() throws IllegalArgumentException {
        stack.push("element");
        stack.push("element2");
        stack.pop();
        stack.pop();
        boolean expected = stack.isEmpty();
        assertTrue(expected, "stack is not empty");
    }
}