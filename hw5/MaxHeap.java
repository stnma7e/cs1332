import java.util.ArrayList;
/**
 * Your implementation of a max heap.
 *
 * @author Sam Delmerico
 * @userid sdelmerico3
 * @GTID 903219343
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[HeapInterface.INITIAL_CAPACITY];
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "list of data passed in was null");
        }
        backingArray = (T[]) new Comparable[2 * data.size() + 1];

        // start at element size/2
        // middle index; last node guaranteed to have at least one child
        // perform downheap on each element from size/2 to 1

        size = data.size();
        for (int i = 0; i < size; i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException(
                        "data at index" + i + "was null");
            }

            backingArray[i + 1] = data.get(i);
        }
        for (int i = size / 2; i >= 1; i--) {
            downheap(i);
        }
    }


    @Override
    public void add(T item) {
        // add new item to deppest leftmost location
        // upheap that node
        if (item == null) {
            throw new IllegalArgumentException("item to add cannot be null");
        }

        size += 1;
        if (size >= backingArray.length) {
            regrowArray();
        }

        backingArray[size] = item;
        upheap(size);
    }

    /**
     * Takes node and organizes it in the max heap accordingly.
     *
     * @param i the index of the node to heapify
     */
    private void upheap(int i) {
        if (i > 1 && backingArray[i] != null) {
            int parent = i / 2;
            if (backingArray[parent].compareTo(backingArray[i]) < 0) {
                swap(parent, i);
                upheap(parent);
            }
        }
    }

    /**
     * Takes node and organizes it in the max heap accordingly.
     *
     * @param i the index of the node to heapify
     */
    private void downheap(int i) {
        int leftIndex = i * 2;
        int rightIndex = i * 2 + 1;
        T left = null;
        T right = null;

        if (backingArray.length > leftIndex) {
            left = backingArray[leftIndex];
        }
        if (backingArray.length > rightIndex) {
            right = backingArray[rightIndex];
        }

        if (backingArray[i] == null) {
            return;
        }

        if (left != null && right != null) {
            if (backingArray[i].compareTo(left) > 0
                    && backingArray[i].compareTo(right) > 0) {
                return;
            } else if (left.compareTo(right) > 0) {
                swap(i, leftIndex);
                downheap(leftIndex);
            } else {
                swap(i, rightIndex);
                downheap(rightIndex);
            }
        } else if (left == null && right == null) {
            return;
        }
        if (left == null) {
            if (backingArray[i].compareTo(right) < 0) {
                swap(i, rightIndex);
                downheap(rightIndex);
            }
        } else if (right == null) {
            if (backingArray[i].compareTo(left) < 0) {
                swap(i, leftIndex);
                downheap(leftIndex);
            }
        }
    }

    /**
     * Swaps the values of two nodes in the heap.
     *
     * @param from the source index of the node to swap
     * @param to the target index of the swapped node
     */
    private void swap(int from, int to) {
        T tmp = backingArray[to];
        backingArray[to] = backingArray[from];
        backingArray[from] = tmp;
    }

    @Override
    public T remove() {
        // replace root with node that is deepest and farthest to right
        // this keeps shape property of the heap
        // downheap root
        // downheap picks max of children to swap with
        // continues until no more swaps occur

        if (size == 0) {
            throw new java.util.NoSuchElementException("heap is empty");
        }
        T ret = backingArray[1];

        swap(1, size);
        backingArray[size] = null;
        downheap(1);

        size -= 1;
        return ret;
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

    @Override
    public void clear() {
        for (int i = 1; i <= size; i++) {
            backingArray[i] = null;
        }
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Regrows the backingArray to 2x the current length.
     *
     */
    private void regrowArray() {
        T[] tmp = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            tmp[i] = backingArray[i];
        }

        this.backingArray = tmp;
    }
}
