package designpattern.builder;

import designpattern.XMLUtil;

public class Client {
    public static void main(String[] args) {
        Builder builder = (Builder) XMLUtil.getBeanFromConfig("builder");
        Director director = new Director(builder);
        Product product = director.construct();
        System.out.println(product);
    }
}
