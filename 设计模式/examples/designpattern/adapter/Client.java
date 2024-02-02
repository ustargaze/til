package designpattern.adapter;

import designpattern.XMLUtil;

public class Client {
    public static void main(String[] args) {
        Target target = (Target) XMLUtil.getBeanFromConfig("adapter");
        target.request();
    }
}
