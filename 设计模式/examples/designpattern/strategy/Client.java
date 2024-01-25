package designpattern.strategy;

import designpattern.XMLUtil;

public class Client {

    public static void main(String[] args) {
        Strategy strategy = (Strategy) XMLUtil.getBeanFromConfig("strategy");
        Context context = new Context(strategy);
        context.algorithm();
    }
}
