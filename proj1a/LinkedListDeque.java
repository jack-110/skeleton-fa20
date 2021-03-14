public class LinkedListDeque<T> implements Deque<T> {

    private Node Node;
    private Node sentinel;
    private int size;

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {

    }

    @Override
    public void addFirst(T item) {

    }

    @Override
    public void addLast(T item) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void printDeque() {

    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    /**
     * Same as get, but uses recursion.
     * @param index
     * @return
     */
    public T getRecursive(int index) {
        return null;
    }

    private class Node {
        public T item;
        public Node prev;
        public Node next;
        public Node(T x, Node p, Node n) {
            item = x;
            prev = p;
            next = n;
        }
    }
}