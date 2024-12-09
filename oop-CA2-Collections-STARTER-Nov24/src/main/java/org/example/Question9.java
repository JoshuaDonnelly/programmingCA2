package org.example;

import java.util.Scanner;
/**
 *  Name: Dylan Smyth
 *  Class Group: SD2a
 */

import java.util.*;

public class Question9
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an arithmetic expression: ");
        String expression = scanner.nextLine();

        try {
            double result = evaluateExpression(expression);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error evaluating expression: " + e.getMessage());
        }
    }

    public static double evaluateExpression(String expression) throws Exception {
        // Step 1: Convert infix to postfix
        List<String> postfix = infixToPostfix(expression);

        // Step 2: Evaluate the postfix expression
        return evaluatePostfix(postfix);
    }

    private static List<String> infixToPostfix(String expression) throws Exception {
        List<String> postfix = new ArrayList<>();
        Stack<Character> operators = new Stack<>();
        StringBuilder number = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                // Build multi-digit numbers and decimals
                number.append(c);
            } else {
                if (number.length() > 0) {
                    postfix.add(number.toString());
                    number.setLength(0); // Reset number
                }

                if (c == '(') {
                    operators.push(c);
                } else if (c == ')') {
                    // Pop operators until '(' is found
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        postfix.add(String.valueOf(operators.pop()));
                    }
                    if (operators.isEmpty() || operators.pop() != '(') {
                        throw new Exception("Mismatched parentheses");
                    }
                } else if (isOperator(c)) {
                    // Pop operators with higher or equal precedence
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                        postfix.add(String.valueOf(operators.pop()));
                    }
                    operators.push(c);
                }
            }
        }

        // Add remaining number
        if (number.length() > 0) {
            postfix.add(number.toString());
        }

        // Pop remaining operators
        while (!operators.isEmpty()) {
            char op = operators.pop();
            if (op == '(') throw new Exception("Mismatched parentheses");
            postfix.add(String.valueOf(op));
        }

        return postfix;
    }

    private static double evaluatePostfix(List<String> postfix) throws Exception {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token.charAt(0))) {
                if (stack.size() < 2) throw new Exception("Invalid expression");
                double b = stack.pop();
                double a = stack.pop();
                stack.push(applyOperator(a, b, token.charAt(0)));
            }
        }

        if (stack.size() != 1) throw new Exception("Invalid expression");
        return stack.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char c) {
        return (c == '+' || c == '-') ? 1 : (c == '*' || c == '/') ? 2 : -1;
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static double applyOperator(double a, double b, char operator) throws Exception {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new Exception("Division by zero");
                yield a / b;
            }
            default -> throw new Exception("Unknown operator");
        };
    }
}
