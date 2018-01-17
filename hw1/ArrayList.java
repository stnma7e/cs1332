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

    /**
    * Helper function to add elements to the list at a certain index or at the
    * back of the list.
    *
    * @param index The index where you want the new element.
    * @param data Any object of type T.
    * @param back Whether we are adding to the back of the list.
    * @throws java.lang.IndexOutOfBoundsException if index < 0 or
    * index >= size.
    * @throws java.lang.IllegalArgumentException if data is null.
    */
    private void add(int index, T data, boolean back) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (data == null) {
            throw new IllegalArgumentException();
        }

        if (size + 1 > backingArray.length) {
            regrowArray(index + 1);
        }

        if (back) {
            for (int i = backingArray.length - 1; i >= 0; i--) {
                if (backingArray[i] != null) {
                    if (i + 2 > backingArray.length) {
                        regrowArray(i + 2);
                    }
                    backingArray[i + 1] = data;

                    size  += 1;
                    return;
                } else if (i == 0) {
                    backingArray[0] = data;
                }
            }
        } else {
            for (int i = backingArray.length - 2; i >= index; i--) {
                backingArray[i + 1] = backingArray[i];
            }
            backingArray[index] = data;
        }

        size += 1;
    }

    @Override
    public void addAtIndex(int index, T data) {
        add(index, data, false);
    }

    @Override
    public void addToFront(T data) {
        add(0, data, false);
    }

    @Override
    public void addToBack(T data) {
        add(size, data, true);
    }

    /**
    * Helper function to remove elements to the list at a certain index or from
    * the back of the list.
    *
    * @param index The index where you want the new element.
    * @param back Whether we are adding to the back of the list.
    * @return Returns the element that was removed from the list.
    * @throws java.lang.IndexOutOfBoundsException if index < 0 or
    * index >= size.
    */
    private T remove(int index, boolean back) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        T ret = backingArray[index];

        if (back) {
            for (int i = backingArray.length - 1; i >= 0; i--) {
                if (backingArray[i] != null) {
                    ret = backingArray[i];
                    size -= 1;
                    backingArray[i] = null;
                    return ret;
                }
            }
        } else {
            for (int i = index + 1; i < backingArray.length; i++) {
                backingArray[i - 1] = backingArray[i];
            }
        }

        size -= 1;
        backingArray[size] = null;

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
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

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
        backingArray = (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }

    /**
    * Resizes the backingArray so that it has at least the specified capacity.
    *
    * @param cap The maximum capacity that you want the list to be able to hold.
    */
    private void regrowArray(int cap) {
        T[] tmp = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            tmp[i] = backingArray[i];
        }

        this.backingArray = tmp;

        if (backingArray.length < cap) {
            regrowArray(cap);
        }
    }
}
