/**
 * Your implementation of a linked queue. It should NOT be circular.
 *
 * @author Sam Delmerico
 * @userid sdelmerico3
 * @GTID 903219343
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        if (head == null) {
            throw new java.util.NoSuchElementException();
        }

        LinkedNode node = head;
        T ret = (T) head.getData();
        head = head.getNext();

        size -= 1;

        if (size == 0) {
            head = null;
            tail = null;
        }

        return ret;
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        LinkedNode node = new LinkedNode<T>(data);
        if (size == 0) {
            head = node;
        } else {
            tail.setNext(node);
        }

        tail = node;
        size += 1;
    }

    @Override
    public T peek() {
        if (head == null) {
            return null;
        }

        return (T) head.getData();
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }
}
