package designpattern.observer;

public class ConcreteSubject extends Subject{
    @Override
    public void notifyObservers() {
        System.out.println("ConcreteSubject: notify all observers.");
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
