package designpattern.simplefactory;

public class Factory {
    public static Product createProduct(String name) {
        switch (name) {
            case "A":
                return new ConcreteProductA();
            case "B":
                return new ConcreteProductB();
            default:
                // throw new RuntimeException("invalid product name");
                return null;
        }
    }
}
