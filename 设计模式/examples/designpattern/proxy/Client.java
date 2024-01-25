package designpattern.proxy;

import designpattern.XMLUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        Subject subject = (Subject) XMLUtil.getBeanFromConfig("proxy");
        subject.request1();
        subject.request2();

        // dynamic proxy example
        Subject realSubject = new RealSubject();
        InvocationHandler handler = new LogHandler(realSubject);
        Subject proxy = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, handler);
        proxy.request1();
        proxy.request2();

        // save proxy class to disk
//        byte[] classFile = ProxyGenerator.generateProxyClass("Proxy$0", new Class[]{Subject.class});
//        String paths = Subject.class.getResource(".").getPath();
//        System.out.println(paths);
//        FileOutputStream out = null;
//        try {
//            out = new FileOutputStream(paths + "Proxy$0" + ".class");
//            out.write(classFile);
//            out.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
