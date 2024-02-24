package designpattern.memento;

public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator("state 1");
        System.out.println(originator.getState());

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());

        originator.setState("state 2");
        System.out.println(originator.getState());

        originator.restoreMemento(caretaker.getMemento());
        System.out.println(originator.getState());
    }
}
