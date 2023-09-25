package edu.trinity;

import java.util.Stack;
public class RPNCalculator {
    public static double evaluate(String expr) {
        Stack<Double> stack = new Stack<>();

        String[] tokens = expr.split(" ");

        for (String token : tokens) {
            if (isOperand(token))
                stack.push(Double.parseDouble(token));
            else if (isOperator(token)) {
                double opn2 = stack.pop();
                double opn1 = stack.pop();
                stack.push(applyOperator(opn1, opn2, token));
            }
            else
                throw new IllegalArgumentException("Invalid Operator");
        }

        while (stack.size() != 1)
            stack.pop();

        return stack.pop();
    }

    private static boolean isOperand(String opn) {
        try {
            Double.parseDouble(opn);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static boolean isOperator(String opr) {
        return opr.equals("+") || opr.equals("-") || opr.equals("*") || opr.equals("/");
    }

    private static double applyOperator(double opn1, double opn2, String opr){
        return switch (opr) {
            case "+" -> opn1 + opn2;
            case "-" -> opn1 - opn2;
            case "*" -> opn1 * opn2;
            case "/" -> {
                if (opn2 == 0)
                    throw new IllegalArgumentException("Division by zero");
                yield opn1 / opn2;
            }
            default -> throw new IllegalArgumentException("Invalid Operator");
        };
    }
}
