package taskRunner.impl;


public class Queue<E> {
    private Object[] items;
    private int count;
    private int putIndex;
    private int takeIndex;

    static <E> E cast(Object item) {
        return (E) item;
    }

    public Queue(int capacity) {
        items = new Object[capacity];
    }

    public synchronized boolean offer(E e) {
        if (count == items.length)
            return false;
        else {
            insert(e);
            return true;
        }
    }

    public synchronized E poll() {
        return (count == 0) ? null : extract();

    }

    public synchronized boolean isEmpty() {
        return count==0;
    }

    private void insert(E x) {
        items[putIndex] = x;
        putIndex = inc(putIndex);
        ++count;
//        notEmpty.signal();
    }

    private int inc(int i) {
        return (++i == items.length) ? 0 : i;
    }

    private E extract() {
        final Object[] items = this.items;
        E x = this.<E>cast(items[takeIndex]);
        items[takeIndex] = null;
        takeIndex = inc(takeIndex);
        --count;
//        notFull.signal();
        return x;
    }
}
