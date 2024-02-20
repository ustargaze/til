package designpattern.command;

public class ConcreteCommand extends Command {
    private Receiver receiver = new Receiver();

    @Override
    public void execute() {
        System.out.println("ConcreteCommand: executing.");
        receiver.action();
    }
}
