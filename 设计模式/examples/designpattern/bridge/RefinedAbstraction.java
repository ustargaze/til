package designpattern.bridge;

public class RefinedAbstraction extends Abstraction {
    @Override
    public void operation() {
        System.out.println("This is operation of RefinedAbstraction.");
        this.implementor.operationImpl();
    }
}
