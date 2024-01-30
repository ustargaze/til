package designpattern.bridge;

public class ConcreteImplementorA implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("This is operationImpl of ConcreteImplementorA.");
    }
}
