package designpattern.mediator;

public class Client {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Colleague colleagueA = new ConcreteColleagueA(mediator, "ColleagueA");
        Colleague colleagueB = new ConcreteColleagueB(mediator, "ColleagueB");
        mediator.register(colleagueA);
        mediator.register(colleagueB);

        colleagueA.notifyOthers();
    }
}
