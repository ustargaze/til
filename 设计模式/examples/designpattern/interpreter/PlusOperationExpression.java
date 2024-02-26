package designpattern.interpreter;

public class PlusOperationExpression extends AbstractExpression {
    private AbstractExpression left;
    private AbstractExpression right;

    public PlusOperationExpression(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}
