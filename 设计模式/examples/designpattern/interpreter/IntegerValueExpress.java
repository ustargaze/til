package designpattern.interpreter;

public class IntegerValueExpress extends AbstractExpression{
    private int value;

    public IntegerValueExpress(int value) {
        this.value = value;
    }

    @Override
    public int interpret() {
        return value;
    }
}
