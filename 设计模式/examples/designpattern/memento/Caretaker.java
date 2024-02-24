package designpattern.memento;

public class Caretaker {
    private Originator.Memento memento;

    public Originator.Memento getMemento() {
        return memento;
    }

    public void setMemento(Originator.Memento memento) {
        this.memento = memento;
    }
}
