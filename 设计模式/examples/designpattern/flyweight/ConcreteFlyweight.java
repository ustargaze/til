package designpattern.flyweight;

public class ConcreteFlyweight implements Flyweight {
    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(String extrinsicState) {
        System.out.printf("ConcreteFlyweight: intrinsicState = %s, extrinsicState = %s\n", intrinsicState, extrinsicState);
    }
}
