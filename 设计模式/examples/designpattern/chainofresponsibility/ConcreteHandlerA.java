package designpattern.chainofresponsibility;

public class ConcreteHandlerA extends Handler {
    @Override
    public void handleRequest(String request) {
        System.out.println("The request that ConcreteHandlerA received is '" + request + "'.");
        if (this.successor != null) {
            this.successor.handleRequest("The request that ConcreteHandlerA handled.");
        }
    }
}
