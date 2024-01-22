package designpattern.factorymethod;

import designpattern.XMLUtil;

public class Client {
    public static void main(String[] args)  {
        Factory factory = (Factory) XMLUtil.getBean(Client.class);
        Product product = factory.factoryMethod();
        product.method();
    }
}