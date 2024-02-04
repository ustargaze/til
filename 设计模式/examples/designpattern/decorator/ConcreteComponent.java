package designpattern.decorator;

public class ConcreteComponent extends Component {
    @Override
    public void operation() {
        System.out.println("This is operation of ConcreteComponent.");
    }
}
