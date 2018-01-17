/**
 * Your implementation of a circular singly linked list.
 *
 * @author Sam Delmerico
 * @userid sdelmerico3
 * @GTID 903219343
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (data == null) {
            throw new IllegalArgumentException();
        }

        LinkedListNode node = new LinkedListNode(data, head);

        if (head == null) {
            node.setNext(node);
            head = node;
        } else if (index == 0 || index == size) {
            node.setNext(head.getNext());
            head.setNext(node);

            T headData = head.getData();
            head.setData((T) node.getData());
            node.setData((T) headData);

            if (index == size) {
                head = node;
            }
        } else {
            LinkedListNode nodeToMove = getNode(index - 1);
            node.setNext(nodeToMove.getNext());
            nodeToMove.setNext(node);
        }

        size += 1;
    }

    @Override
    public void addToFront(T data) {
        addAtIndex(data, 0);
    }

    @Override
    public void addToBack(T data) {
        addAtIndex(data, size);
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        T ret = null;

        if (index == 0) {
            LinkedListNode newHead = head.getNext();
            ret = (T) head.getData();
            head.setData((T) newHead.getData());
            head.setNext(newHead.getNext());
        } else {
            LinkedListNode node = getNode(index - 1);
            ret = (T) node.getNext().getData();
            node.setNext(node.getNext().getNext());
        }

        size -= 1;

        if (size == 0) {
            head = null;
        }

        return ret;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }

        return removeAtIndex(0);
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
    
        return removeAtIndex(size - 1);
    }

    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        Object[] seen = toArray();
        for (int i = size - 1; i >= 0; i--) {
            if (seen[i].equals(data)) {
                return removeAtIndex(i);
            }
        }

        return null;
    }

    /**
     * Returns the LinkedListNode at the specified index.
     *
     * @param index the index of the requested element
     * @return the node stored at index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    LinkedListNode<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        LinkedListNode currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    @Override
    public T get(int index) {
        return (T) getNode(index).getData();
    }

    @Override
    public Object[] toArray() {
        Object[] ret = new Object[size];
        LinkedListNode currentNode = head;
        for (int i = 0; i < size; i++) {
            ret[i] = currentNode.getData();
            currentNode = currentNode.getNext();
        }

        return ret;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}
