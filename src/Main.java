import math.Validator;
import stacks.Stack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Scanner scanner = new Scanner(System.in);

        String[] terms;

        do {
            System.out.println("Enter your terms: ");
            String input = scanner.nextLine();
            terms = input.split(" ");
        } while (!Validator.isValid(terms));

        Stack stack = new Stack(terms.length);

        for (String term: terms){
            stack.push(term);
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}