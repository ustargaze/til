package designpattern.builder;

import designpattern.XMLUtil;

public class Client {
    public static void main(String[] args) {
        Builder builder = (Builder) XMLUtil.getBean(Client.class);
        Director director = new Director(builder);
        Product product = director.construct();
        System.out.println(product);
    }
}
