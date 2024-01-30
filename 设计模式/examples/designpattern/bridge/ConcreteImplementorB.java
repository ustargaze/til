package designpattern.bridge;

public class ConcreteImplementorB implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("This is operationImpl of ConcreteImplementorB.");
    }
}
