public class Stack<T> {
    int size;
    T[] back;

    public void push(T data) {
        back[size] = data;
        size += 1;
    }
    public T pop() {
        T ret = back[size - 1];
        back[size - 1] = null;

        size -= 1;
        return ret;
    }
    public boolean isEmpty() {
        return size == 0;
    }
}
