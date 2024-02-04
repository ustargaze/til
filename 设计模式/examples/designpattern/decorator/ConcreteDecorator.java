package designpattern.decorator;

public class ConcreteDecorator extends Decorator {
    ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addedBehavior();

    }

    public void addedBehavior() {
        System.out.println("This is addedBehavior of ConcreteDecorator.");
    }
}
