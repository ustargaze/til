package designpattern.facade;

public class ConcreteFacade1 extends AbstractFacade {
    private SubsystemA subsystemA = new SubsystemA();
    private SubsystemB subsystemB = new SubsystemB();
    private SubsystemC subsystemC = new SubsystemC();

    @Override
    public void method() {
        System.out.println("This is method of ConcreteFacade1.");
        subsystemA.methodA();
        subsystemB.methodB();
        subsystemC.methodC();
    }
}
