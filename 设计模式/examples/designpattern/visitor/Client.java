package designpattern.visitor;

import designpattern.XMLUtil;

public class Client {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        Element elementA = new ConcreteElementA("ElementA");
        Element elementB = new ConcreteElementB("ElementB");
        objectStructure.addElement(elementA);
        objectStructure.addElement(elementB);

        Visitor visitor = (Visitor) XMLUtil.getBeanFromConfig("visitor");

        objectStructure.accept(visitor);
    }
}
