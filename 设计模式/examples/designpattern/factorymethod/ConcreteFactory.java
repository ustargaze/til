package designpattern.factorymethod;

public class ConcreteFactory implements Factory {

    @Override
    public Product factoryMethod() {
        System.out.println("Create a ConcreteProduct.");
        return new ConcreteProduct();
    }
}
