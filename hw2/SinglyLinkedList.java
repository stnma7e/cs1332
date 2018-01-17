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

    void add(T data, int index, boolean back) {
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
        } else if (back) {
            LinkedListNode lastNode = head;
            boolean endFound = false;
            while (!endFound) {
                if (lastNode.getNext() == head) {
                    lastNode.setNext(node);
                    endFound = true;
                } else {
                    lastNode = lastNode.getNext();
                }
            }
        } else if (index == 0) {
            head = node;

            LinkedListNode currentNode = head;
            for (int i = 0; i < size; i++) {
                currentNode = currentNode.getNext();
            }

            currentNode.setNext(head);
        } else {
            LinkedListNode nodeToMove = head;
            for (int i = 0; i < index - 1; i++) {
                nodeToMove = nodeToMove.getNext();
            }

            node.setNext(nodeToMove.getNext());
            nodeToMove.setNext(node);
        }

        size += 1;
    }

    @Override
    public void addAtIndex(T data, int index) {
        add(data, index, false);
    }

    @Override
    public void addToFront(T data) {
        add(data, 0, false);
    }

    @Override
    public void addToBack(T data) {
        add(data, 0, true);
    }

    T remove(int index, boolean back) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        T ret = null;

        if (back) {
            LinkedListNode node = getNode(size - 1);
            ret = (T) node.getNext().getData();
            node.setNext(head);
        } else if (index == 0) {
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

        return ret;
    }

    @Override
    public T removeAtIndex(int index) {
        return remove(index, false);
    }

    @Override
    public T removeFromFront() {
        return remove(0, false);
    }

    @Override
    public T removeFromBack() {
        return remove(size, true);
    }

    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        Object[] seen = new Object[size];
        LinkedListNode currentNode = head;
        for (int i = 0; i < size; i++) {
            seen[i] = currentNode.getData();
            currentNode = currentNode.getNext();
        }

        for (int i = size - 1; i >= 0; i--) {
            if (seen[i].equals(data)) {
                return removeAtIndex(i);
            }
        }

        return null;
    }

    LinkedListNode<T> getNode(int index) {
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
        return false;

    }

    @Override
    public void clear() {

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
