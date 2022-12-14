package stacks;

public class Stack {
    private final String[] stack;
    private int pointer = 0;

    public Stack(int size){
        stack = new String[size];
    }

    public void push(String element) throws IllegalAccessException {
        if (pointer == stack.length) {
            throw new IllegalAccessException("Stack is full");
        }
        stack[pointer] = element;
        pointer++;
    }

    public String peek(){
        if (pointer >= 1)
            return stack[pointer-1];
        return null;
    }

    public String pop(){
        String lastElement = peek();
        if (pointer > 0){
            pointer--;
        }
        stack[pointer] = null;
        return lastElement;
    }

    public boolean isEmpty(){
        return pointer == 0 && stack[0] == null;
    }
}
