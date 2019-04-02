package vorokhobko.arithmeticExpression;

import java.util.Stack;

/**
 * RPN.
 *
 * Class RPN is the revers polish notation.
 * @author Evgeny Vorokhobko (vorokhobko2011@yandex.ru).
 * @since 01.04.2019.
 * @version 1.
 */
public class RPN {
    /**
     * The class field.
     */
    private static final char END_CHAR = '$';
    /**
     * The class field.
     */
    private StringBuilder input;
    /**
     * The class field.
     */
    private Stack<StringBuilder> output = new Stack<>();
    /**
     * Create constructor.
     * Set start and end symbol $.
     * @param input - expressions to evaluate.
     */
    public RPN(StringBuilder input) {
        input.insert(0, END_CHAR);
        input.insert(input.length(), END_CHAR);
        this.input = input;
    }
    /**
     * The method gets a reverse Polish notation.
     * @return tag.
     */
    public Stack<StringBuilder> getExpression() {
        Stack<Character> operators = new Stack<>();
        operators.push(input.charAt(0));
        for (int i = 1; i < input.length(); i++) {
            if (isDelimiter(input.charAt(i))) {
                continue;
            } else if (Character.isDigit(input.charAt(i))) {
                StringBuilder digit = new StringBuilder();
                while (!isDelimiter(input.charAt(i)) && !isOperator(input.charAt(i)) && !isEndChar(input.charAt(i))) {
                    digit.append(input.charAt(i));
                    i++;
                    if (i == input.length()) {
                        break;
                    }
                }
                output.push(digit);
                i--;
            } else if (isOperator(input.charAt(i))) {
                if (input.charAt(i) == '(') {
                    operators.push(input.charAt(i));
                } else if (input.charAt(i) == ')') {
                    if (getPriority(input.charAt(i)) < getPriority(operators.peek())) {
                        Character s = operators.pop();
                        while (s != '(') {
                            output.push(new StringBuilder(s.toString()));
                            s = operators.pop();
                        }
                    } else if (getPriority(input.charAt(i)) > getPriority(operators.peek())) {
                        try {
                            throw new Exception();
                        } catch (Exception e) {
                            System.out.println("ERROR INPUT DATA");
                            return null;
                        }
                    } else {
                        operators.pop();
                    }
                } else if (isOperator(input.charAt(i))) {
                    if (getPriority(input.charAt(i)) <= getPriority(operators.peek()))
                        while (getPriority(input.charAt(i)) <= getPriority(operators.peek()))
                            output.push(new StringBuilder(operators.pop().toString()));
                    operators.push(input.charAt(i));
                }
            } else if (isEndChar(input.charAt(i))) {
                Character s = operators.pop();
                while (s != END_CHAR) {
                    output.push(new StringBuilder(s.toString()));
                    s = operators.pop();
                }
            }
        }
        return output;
    }
    /**
     * The method gets solution of RPN.
     * @return tag.
     */
    public Double getSolution() {
        Stack<Double> solution = new Stack<>();
        for (StringBuilder item : output) {
            if (isOperator(item.charAt(0))) {
                double a, b;
                b = Double.parseDouble(solution.pop().toString());
                a = Double.parseDouble(solution.pop().toString());
                solution.push(makeOperation(a, b, item.charAt(0)));
            } else {
                solution.push(Double.parseDouble(item.toString()));
            }
        }
        return solution.pop();
    }
    /**
     * The method checks the number of brackets in the expression.
     * @return tag.
     */
    public boolean validate() {
        int bracket = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                bracket++;
            } else if (input.charAt(i) == ')') {
                bracket--;
            }
            if (!Character.isDigit(input.charAt(i)) &&
                    !isOperator(input.charAt(i)) && input.charAt(i) != END_CHAR) {
                System.out.println("ERROR INPUT DATA");
                return false;
            }
        }
        if (bracket != 0) {
            System.out.println("ERROR INPUT DATA");
            return false;
        }
        return true;
    }
    /**
     * The method makes operation.
     * @param a - number in input line.
     * @param b - number in input line.
     * @return tag.
     */
    private Double makeOperation(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                return 0.00;
        }
    }
    /**
     * The method gets priority of an operation.
     * @param c - char element in input line.
     * @return tag.
     */
    private byte getPriority(char c) {
        switch (c) {
            case '$':
                return 0;
            case '(':
                return 1;
            case ')':
                return 1;
            case '+':
                return 2;
            case '-':
                return 2;
            case '*':
                return 3;
            case '/':
                return 3;
            default:
                return 1;
        }
    }
    /**
     * The method checks if there is a separator.
     * @param c - char element in input line.
     * @return tag.
     */
    private boolean isEndChar(char c) {
        return END_CHAR == c;
    }
    /**
     * The method checks this finite element.
     * @param c - char element in input line.
     * @return tag.
     */
    private boolean isDelimiter(char c) {
        return " ".indexOf(c) != -1;
    }
    /**
     * The method checks that this is an operator
     * @param c - char element in input line.
     * @return tag.
     */
    private boolean isOperator(char c) {
        return "+-*/()".indexOf(c) != -1;
    }
}
