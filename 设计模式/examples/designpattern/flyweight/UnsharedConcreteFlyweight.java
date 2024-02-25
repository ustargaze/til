package designpattern.flyweight;

public class UnsharedConcreteFlyweight implements Flyweight{
    private String intrinsicState;

    public UnsharedConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(String extrinsicState) {
        System.out.printf("UnsharedConcreteFlyweight: intrinsicState = %s, extrinsicState = %s\n", intrinsicState, extrinsicState);
    }
}
