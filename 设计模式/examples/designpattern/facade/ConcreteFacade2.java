package designpattern.facade;

public class ConcreteFacade2 extends AbstractFacade{
    private SubsystemA subsystemA = new SubsystemA();

    @Override
    public void method() {
        System.out.println("This is method of ConcreteFacade2.");
        subsystemA.methodA();
    }
}
