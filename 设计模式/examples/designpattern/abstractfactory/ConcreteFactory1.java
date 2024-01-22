package designpattern.abstractfactory;

public class ConcreteFactory1 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        System.out.println("Create ConcreteProductA1.");
        return new ConcreteProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        System.out.println("Create ConcreteProductB1.");
        return new ConcreteProductB1();
    }
}
