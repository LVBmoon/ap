package src.kargahpishrafte;

import java.util.Stack;
import java.util.Scanner;

public class Session1_homework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter an expression: ");
        String expression = sc.nextLine();
        boolean result = BalancedParentheses(expression);

        if (result) {
            System.out.println("The parentheses are balanced.");
        }
        else {
            System.out.println("The parentheses are not balanced.");
        }
    }

    public static boolean BalancedParentheses(String expression) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return true;
    }
}



