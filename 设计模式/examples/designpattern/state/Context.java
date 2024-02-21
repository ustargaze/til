package designpattern.state;

public class Context {
    private State state;
    private int value;

    public void setState(State state) {
        this.state = state;
    }

    public void valueIncrement() {
        System.out.printf("Context: value updated to %d.\n", ++value);
        if (value % 2 == 0) {
            this.setState(new ConcreteStateA());
        } else {
            this.setState(new ConcreteStateB());
        }
    }

    public void request() {
        state.handle();
    }
}
