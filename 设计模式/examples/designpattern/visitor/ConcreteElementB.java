package designpattern.visitor;

public class ConcreteElementB implements Element {
    private String b;

    public ConcreteElementB(String b) {
        this.b = b;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public void accept(Visitor visitor) {
        System.out.printf("ConcreteElementB:%s is accepting the visit from %s \n", this.b, visitor.getClass().getSimpleName());
        visitor.visit(this);
    }
}
