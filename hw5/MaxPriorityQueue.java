/**
 * Your implementation of a max priority queue.
 *
 * @author Sam Delmerico
 * @userid sdelmerico3
 * @GTID 903219343
 * @version 1.0
 */
public class MaxPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private HeapInterface<T> backingHeap;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a priority queue.
     */
    public MaxPriorityQueue() {
        backingHeap = new MaxHeap();
    }

    @Override
    public void enqueue(T item) {
        try {
            backingHeap.add(item);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "data to enqueue cannot be null");
        }
    }

    @Override
    public T dequeue() {
        try {
            return backingHeap.remove();
        } catch (java.util.NoSuchElementException e) {
            throw new java.util.NoSuchElementException("queue is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap.isEmpty();
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap.size();
    }

    @Override
    public void clear() {
        backingHeap.clear();
    }

    @Override
    public HeapInterface<T> getBackingHeap() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap;
    }

}
