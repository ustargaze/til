package designpattern.state;

public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.valueIncrement();
        context.request();

        context.valueIncrement();
        context.request();
    }
}
