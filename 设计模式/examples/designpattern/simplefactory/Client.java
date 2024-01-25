package designpattern.simplefactory;

import designpattern.XMLUtil;

public class Client {
    public static void main(String[] args) {
        String type = XMLUtil.getConfigValue("simplefactory");
        Product product = Factory.createProduct(type);
        product.method();
    }
}