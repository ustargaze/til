package designpattern.mediator;

public class ConcreteMediator extends Mediator{
    @Override
    public void notifyOthers(Colleague colleague) {
        for (Colleague c : colleagues) {
            if (c != colleague) {
                c.receive(colleague.name);
            }
        }
    }
}
