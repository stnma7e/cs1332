public class InnerNode<T> {
    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> next, Node<T> prev) {
            this.setNext(next);
            this.setPrev(prev);
        }

        void setNext(Node<T> next) {
            this.next = next;
        }

        void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        Node<T> getNext() {
            return next;
        }

        Node<T> getPrev() {
            return prev;
        }
    }
}
