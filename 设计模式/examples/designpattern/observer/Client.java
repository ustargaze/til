package designpattern.observer;

import java.util.Observable;

public class Client {
    public static void main(String[] args) {
        Observer observer = new ConcreteObserver();
        Subject subject = new ConcreteSubject();

        subject.attach(observer);
        subject.notifyObservers();
    }
}
