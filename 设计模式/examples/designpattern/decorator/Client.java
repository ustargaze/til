package designpattern.decorator;

import java.util.Collections;

public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component.operation();
        component = new ConcreteDecorator(component);
        component.operation();
    }
}
