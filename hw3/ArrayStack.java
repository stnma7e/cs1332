/**
 * Your implementation of an array-backed stack.
 *
 * @author Sam Delmerico
 * @userid sdelmerico3
 * @GTID 903219343
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) new Object[StackInterface.INITIAL_CAPACITY];
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * You should replace any spots that you pop from with null. Failure to do
     * so can result in a loss of points.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        if (size < 1) {
            throw new java.util.NoSuchElementException();
        }

        T ret = backingArray[size - 1];
        backingArray[size - 1] = null;

        size -= 1;
        return ret;
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length.
     *
     * @see StackInterface#push(T)
     */
    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        if (backingArray.length < size + 1) {
            regrowArray(backingArray.length * 2);
        }

        backingArray[size] = data;
        size += 1;
    }

    @Override
    public T peek() {
        if (size < 1) {
            return null;
        }

        return backingArray[size - 1];
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
     * Returns the backing array of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    private void regrowArray(int cap) {
        if (backingArray.length >= cap) {
            return;
        }

        T[] tmp = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            tmp[i] = backingArray[i];
        }

        this.backingArray = tmp;
        regrowArray(cap);
    }
}
