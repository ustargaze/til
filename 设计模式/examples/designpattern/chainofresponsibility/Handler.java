package designpattern.chainofresponsibility;

public abstract class Handler {
    Handler successor;

    public abstract void handleRequest(String request);

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
}
