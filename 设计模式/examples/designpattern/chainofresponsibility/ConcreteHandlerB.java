package designpattern.chainofresponsibility;

public class ConcreteHandlerB extends Handler{
    @Override
    public void handleRequest(String request) {
        System.out.println("The request that ConcreteHandlerB received is '" + request + "'.");
        if (this.successor != null) {
            this.successor.handleRequest("The request that ConcreteHandlerB handled.");
        }
    }
}
