package designpattern.composite;

public class Leaf extends Component {
    private final String name;

    Leaf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void operation() {
        System.out.printf("This is operation of Leaf %s.\n", this.name);
    }

    @Override
    public void add(Component c) {
        System.out.println("The add operation is not supported in Leaf");
    }

    @Override
    public void remove(Component c) {
        System.out.println("The remove operation is not supported in Leaf");
    }

    @Override
    public Component getChild(int i) {
        System.out.println("The getChild operation is not supported in Leaf");
        return null;
    }
}
