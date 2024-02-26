package designpattern.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Client {
    public static void main(String[] args) {
        String s = "1 + 2 + 3 - 4 + 1";
        System.out.println(output(handle(s)));
    }

    private static AbstractExpression handle(String s) {
        String[] split = s.split(" ");
        Deque<AbstractExpression> stack = new ArrayDeque<>();
        for (int i = 0; i < split.length; i++) {
            if (Objects.equals(split[i], "+") || Objects.equals(split[i], "-")) {
                AbstractExpression left = stack.pop();
                AbstractExpression right = new IntegerValueExpress(Integer.parseInt(split[i + 1]));
                AbstractExpression operationExpress;
                if (Objects.equals(split[i], "+")) {
                    operationExpress = new PlusOperationExpression(left, right);
                } else {
                    operationExpress = new MinusOperationExpression(left, right);
                }
                stack.push(operationExpress);
                i++;
                continue;
            }
            AbstractExpression express = new IntegerValueExpress(Integer.parseInt(split[i]));
            stack.push(express);
        }
        return stack.pop();
    }

    private static int output(AbstractExpression expression) {
        return expression.interpret();
    }
}
