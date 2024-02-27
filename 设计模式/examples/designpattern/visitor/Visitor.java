package designpattern.visitor;

public abstract class Visitor {
    public abstract void visit(ConcreteElementA element);
    public abstract void visit(ConcreteElementB element);
}
