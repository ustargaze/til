package designpattern.command;

import designpattern.XMLUtil;

public class Client {
    public static void main(String[] args) {
        Command command = (Command) XMLUtil.getBeanFromConfig("command");
        Invoker invoker = new Invoker(command);
        invoker.call();
    }
}
