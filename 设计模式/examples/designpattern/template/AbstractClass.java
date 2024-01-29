package designpattern.template;

public abstract class AbstractClass {
    public void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
        primitiveOperation3();
    }

    public abstract void primitiveOperation1();

    public void primitiveOperation2() {
        System.out.println("This is primitiveOperation2 of AbstractClass.");
    }

    public void primitiveOperation3() {}
}
