package designpattern.mediator;

public class ConcreteColleagueA extends Colleague{
    public ConcreteColleagueA(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void receive(String name) {
        System.out.printf("%s: receive message form %s.\n", this.name, name);
    }
}
