package designpattern.flyweight;

public class Client {
    public static void main(String[] args) {
        Flyweight flyweight1 = FlyweightFactory.getFlyweight("state1");
        Flyweight flyweight2 = FlyweightFactory.getFlyweight("state1");

        flyweight1.operation("exState1");
        flyweight2.operation("exState2");
    }
}
