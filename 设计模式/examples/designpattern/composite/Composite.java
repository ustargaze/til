package designpattern.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component{
    private final List<Component> children = new ArrayList<>();

    @Override
    public void operation() {
        System.out.println("This is operation of Composite.");
        for (Component child : children) {
            child.operation();
        }
    }

    @Override
    public void add(Component c) {
        children.add(c);
    }

    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    @Override
    public Component getChild(int i) {
        return children.get(i);
    }
}
