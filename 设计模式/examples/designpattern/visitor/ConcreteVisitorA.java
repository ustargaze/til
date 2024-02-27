package designpattern.visitor;

public class ConcreteVisitorA extends Visitor {
    @Override
    public void visit(ConcreteElementA element) {
        System.out.printf("ConcreteVisitorA is visiting ConcreteElementA:%s \n", element.getA());
    }

    @Override
    public void visit(ConcreteElementB element) {
        System.out.printf("ConcreteVisitorA is visiting ConcreteElementB:%s \n", element.getB());
    }
}
