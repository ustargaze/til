package designpattern.iterator;

public class ConcreteAggregate<T> implements Aggregate<T> {
    public T[] values;

    ConcreteAggregate(T[] values) {
        this.values = values;
    }

    @Override
    public Iterator<T> createIterator() {
        return new ConcreteIterator<>(this);
    }
}
