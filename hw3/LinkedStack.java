/**
 * Your implementation of a linked stack. It should NOT be circular.
 *
 * @author Sam Delmerico
 * @userid sdelmerico3
 * @GTID 903219343
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {
        if (head == null) {
            throw new java.util.NoSuchElementException();
        }

        LinkedNode ret = head;
        head = ret.getNext();

        size -= 1;
        return (T) ret.getData();
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        LinkedNode newNode = new LinkedNode(data);
        newNode.setNext(head);
        head = newNode;

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
     * Returns the head of this stack.
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
}
