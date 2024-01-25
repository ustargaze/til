package designpattern.strategy;

public class Context {
    private final Strategy strategy;

    Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void algorithm() {
        strategy.algorithm();
    }
}
