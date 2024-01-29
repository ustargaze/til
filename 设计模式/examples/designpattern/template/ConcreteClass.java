package designpattern.template;

public class ConcreteClass extends AbstractClass {
    @Override
    public void primitiveOperation1() {
        System.out.println("This is primitiveOperation1 of ConcreteClass.");
    }

    @Override
    public void primitiveOperation3() {
        System.out.println("This is primitiveOperation3 of ConcreteClass.");
    }
}
