package designpattern.strategy;

public class ConcreteStrategy implements Strategy {
    @Override
    public void algorithm() {
        System.out.println("This is algorithm of ConcreteStrategy.");
    }
}
