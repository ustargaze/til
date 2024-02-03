package designpattern.composite;

public class Client {
    public static void main(String[] args) {
        Leaf leaf1 = new Leaf("leaf1");
        Leaf leaf2 = new Leaf("leaf2");
        Leaf leaf3 = new Leaf("leaf3");

        leaf1.operation();
        leaf1.add(leaf2);
        leaf1.remove(leaf2);
        leaf1.getChild(0);

        Composite composite = new Composite();
        composite.add(leaf1);
        composite.add(leaf2);
        composite.add(leaf3);

        composite.operation();

        Leaf child = (Leaf) composite.getChild(1);
        System.out.printf("The second child is %s.\n", child.getName());

        composite.remove(leaf2);
        child = (Leaf) composite.getChild(1);
        System.out.printf("Now, %s becomes the second child after removing the original second child.\n", child.getName());
    }
}
