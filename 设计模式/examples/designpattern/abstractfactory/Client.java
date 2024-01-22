package designpattern.abstractfactory;

import designpattern.XMLUtil;

public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = (AbstractFactory) XMLUtil.getBean(Client.class);
        AbstractProductA productA = factory.createProductA();
        AbstractProductB productB = factory.createProductB();
        productA.method();
        productB.method();
    }
}