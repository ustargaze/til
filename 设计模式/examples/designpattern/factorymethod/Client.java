package designpattern.factorymethod;

import designpattern.XMLUtil;

public class Client {
    public static void main(String[] args)  {
        Factory factory = (Factory) XMLUtil.getBeanFromConfig("factorymethod");
        Product product = factory.factoryMethod();
        product.method();
    }
}