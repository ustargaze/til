package designpattern.visitor;

public class ConcreteElementA implements Element{
    private String a;

    public ConcreteElementA(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public void accept(Visitor visitor) {
        System.out.printf("ConcreteElementA:%s is accepting the visit from %s \n", this.a, visitor.getClass().getSimpleName());
        visitor.visit(this);
    }
}
