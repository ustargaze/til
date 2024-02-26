package designpattern.interpreter;

public class MinusOperationExpression extends AbstractExpression {
    private AbstractExpression left;
    private AbstractExpression right;

    public MinusOperationExpression(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() - right.interpret();
    }
}
