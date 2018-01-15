/**
 * Your implementation of an ArrayList.
 *
 * @author Sam Delmerico
 * @userid sdelmerico3
 * @GTID 903219343
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
    }

    void add(int index, T data) {
        if (index < 0) {
            return;
        }

        if (size + 1 > backingArray.length) {
            regrowArray();
        }

        if (index == size) {
            backingArray[size] = data;
        } else {
            for (int i = size; i >= index; i--) {
                backingArray[i + 1] = backingArray[i];
            }
            backingArray[index] = data;
        }

        size += 1;
    }

    @Override
    public void addAtIndex(int index, T data) {
        add(index, data);
    }

    @Override
    public void addToFront(T data) {
        add(0, data);
    }

    @Override
    public void addToBack(T data) {
        add(size, data);
    }

    T remove(int index) {
        if (index > size || index < 0) {
            return null;
        }

        T ret = backingArray[index];

        if (index != size) {
            for (int i = index + 1; i < size; i++) {
                backingArray[i - 1] = backingArray[i];
            }
        }

        size -= 1;
        backingArray[size] = null;

        return ret;
    }

    @Override
    public T removeAtIndex(int index) {
        return remove(index);
    }

    @Override
    public T removeFromFront() {
        return remove(0);
    }

    @Override
    public T removeFromBack() {
        return remove(size);
    }

    @Override
    public T get(int index) {
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }

    void regrowArray() {
        T[] tmp = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            tmp[i] = backingArray[i];
        }

        this.backingArray = tmp;
    }
}
