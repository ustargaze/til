package designpattern.iterator;

public class ConcreteIterator<T> implements Iterator<T> {
    private ConcreteAggregate<T> aggregate;
    private int cursor;

    ConcreteIterator(ConcreteAggregate<T> aggregate) {
        this.aggregate = aggregate;
        this.cursor = 0;
    }

    @Override
    public void first() {
        this.cursor = 0;
    }

    @Override
    public void next() {
        this.cursor++;
    }

    @Override
    public boolean hasNext() {
        return this.cursor < aggregate.values.length;
    }

    @Override
    public T currentItem() {
        return this.aggregate.values[this.cursor];
    }
}
