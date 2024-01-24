package designpattern.proxy;

public class RealSubject implements Subject {
    @Override
    public void request1() {
        System.out.println("This is RealSubject request1.");
    }

    @Override
    public void request2() {
        System.out.println("This is RealSubject request2.");
    }
}
