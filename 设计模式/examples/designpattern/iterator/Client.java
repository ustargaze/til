package designpattern.iterator;

public class Client {
    public static void main(String[] args) {
        Integer[] values = new Integer[10];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }

        Aggregate<Integer> aggregate = new ConcreteAggregate<>(values);
        Iterator<Integer> iterator = aggregate.createIterator();
        while(iterator.hasNext()) {
            int item = iterator.currentItem();
            System.out.println(item);
            iterator.next();
        }
        iterator.first();
        System.out.println(iterator.currentItem());
    }
}
