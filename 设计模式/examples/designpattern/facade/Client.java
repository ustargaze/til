package designpattern.facade;

import designpattern.XMLUtil;

public class Client {
    public static void main(String[] args) {
        AbstractFacade facade = (AbstractFacade) XMLUtil.getBeanFromConfig("facade");
        facade.method();
    }
}
