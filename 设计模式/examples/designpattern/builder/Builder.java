package designpattern.builder;

public abstract class Builder {
    final Product product = new Product();

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    public Product getResult() {
        return this.product;
    }
}
