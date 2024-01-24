package designpattern.proxy;

public class Proxy implements Subject{

    private final RealSubject realSubject = new RealSubject();

    public void preRequest() {
        System.out.println("This is Proxy preRequest.");
    }

    public void postRequest() {
        System.out.println("This is Proxy postRequest.");
    }

    @Override
    public void request1() {
        preRequest();
        realSubject.request1();
        postRequest();
    }

    @Override
    public void request2() {
        preRequest();
        realSubject.request2();
        postRequest();
    }
}
