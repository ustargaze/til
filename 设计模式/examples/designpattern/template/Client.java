package designpattern.template;

import designpattern.XMLUtil;

public class Client {

    public static void main(String[] args) {
        AbstractClass abstractClass = (AbstractClass) XMLUtil.getBeanFromConfig("template");
        abstractClass.templateMethod();
    }
}
