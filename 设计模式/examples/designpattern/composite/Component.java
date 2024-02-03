package designpattern.composite;

public abstract class Component {
    public abstract void operation();

    public abstract void add(Component c);

    public abstract void remove(Component c);

    public abstract Component getChild(int i);
}
