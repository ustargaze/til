package designpattern.abstractfactory;

public class ConcreteFactory2 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        System.out.println("Create ConcreteProductA2.");
        return new ConcreteProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        System.out.println("Create ConcreteProductB2.");
        return new ConcreteProductB2();
    }
}
