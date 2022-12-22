package stacks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StackTest {

    private static Stack stack;

    @BeforeEach
    public void prepareData() {
        stack = new Stack(2);
    }

    @Test
    void pushTest() throws IllegalArgumentException {
        stack.push("element");
        Assertions.assertEquals("element", stack.peek(), "nothing was pushed to stack");
    }

    @Test
    void pushTest2() throws IllegalArgumentException {
        stack.push("element");
        stack.push("element2");
        assertThrows(IllegalArgumentException.class,
                () -> stack.push("element3"));
    }

    @Test
    void peekTest() throws IllegalArgumentException {
        stack.push("string");
        Assertions.assertEquals("string", stack.peek(), "wrong element was picked");
    }

    @Test
    void peekTest2() throws IllegalArgumentException {
        Assertions.assertNull(stack.peek(), "stack is not empty");
    }

    @Test
    void pop() throws IllegalArgumentException {
        stack.push("firstElement");
        stack.push("secondElement");
        Assertions.assertEquals("secondElement", stack.pop(), "false element was deleted");
        Assertions.assertEquals("firstElement", stack.peek(), "the last element in stack is wrong");
    }

    @Test
    void isEmpty() throws IllegalArgumentException {
        stack.push("fistElement");
        stack.push("secondElement");
        stack.pop();
        stack.pop();
        Assertions.assertTrue(stack.isEmpty(), "stack is not empty");
    }
}