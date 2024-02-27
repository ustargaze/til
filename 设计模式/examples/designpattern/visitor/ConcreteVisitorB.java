package designpattern.visitor;

public class ConcreteVisitorB extends Visitor{
    @Override
    public void visit(ConcreteElementA element) {
        System.out.printf("ConcreteVisitorB is visiting ConcreteElementA:%s \n", element.getA());
    }

    @Override
    public void visit(ConcreteElementB element) {
        System.out.printf("ConcreteVisitorB is visiting ConcreteElementB:%s \n", element.getB());
    }
}
