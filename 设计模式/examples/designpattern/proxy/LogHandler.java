package designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class LogHandler implements InvocationHandler {
    private final Object object;

    LogHandler(Object object) {
        this.object = object;
    }

    private void before() {
        System.out.printf("log start time [%s] %n", new Date());
    }

    private void after() {
        System.out.printf("log end time [%s] %n", new Date());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(object, args);
        after();
        return result;
    }
}
