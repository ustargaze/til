package designpattern.prototype;

public class Client {
    public static void main(String[] args) {
        ConcretePrototype concretePrototype = new ConcretePrototype();
        concretePrototype.setAttr("sun day");
        ConcretePrototype copy = (ConcretePrototype) concretePrototype.clone();
        System.out.println(copy.getAttr());
    }
}
