package designpattern.chainofresponsibility;

public class Client {
    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        handlerA.setSuccessor(handlerB);

        handlerA.handleRequest("original request");
    }
}
