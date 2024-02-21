package designpattern.state;

public class ConcreteStateB extends State{
    @Override
    public void handle() {
        System.out.println("ConcreteStateB: handle!");
    }
}
