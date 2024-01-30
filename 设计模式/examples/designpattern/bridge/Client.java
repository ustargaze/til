package designpattern.bridge;

import designpattern.XMLUtil;

public class Client {
    public static void main(String[] args) {
        Abstraction abstraction = (Abstraction) XMLUtil.getBeanFromConfig("bridge.abstraction");
        Implementor implementor = (Implementor) XMLUtil.getBeanFromConfig("bridge.implementor");
        abstraction.setImplementor(implementor);
        abstraction.operation();
    }
}
