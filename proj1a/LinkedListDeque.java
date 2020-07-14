/** Construct LinkedList Deque using circular sentinel topology.
 * @author 517_JY
 */
public class LinkedListDeque<T> {
    /** The first item (if it exists) is at sentinel.next,
     *  The last item (if it exists)'s next is sentinel.
     */
    private class Node {
        /** Gets the previous Node in the deque. */
        private Node prev;

        /** Type T item that needs to be stored in the deque. */
        private T item;

        /** Gets the next Node in the deque. */
        private Node next;

        /** Node constructor.
         *  @param p points to the previous Node in the deque
         *  @param i (type T) item information
         *  @param n points to the next Node in the deque
         */
        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /** Records the size of the array. */
    private int size;

    /** A special Node that ensure the safety and consistency of the deque. */
    private Node sentinel;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        T t = (T) new Object();
        sentinel = new Node(null, t, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Adds an item of type T to the front of the deque.
     *  A single such operation must take "constant time"
     *  i.e. execution time should not depend on the size of the deque.
     * @param item pending item (type T) needs to be added
     */
    public void addFirst(T item) {
        Node temp = sentinel.next;
        Node target = new Node(sentinel, item, temp);
        temp.prev = target;
        sentinel.next = target;
        size++;
    }

    /** Adds an item of type T to the back of the deque.
     *  @param item pending item (type T) needs to be added
     */
    public void addLast(T item) {
        Node temp = sentinel.prev;
        Node target = new Node(temp, item, sentinel);
        temp.next = target;
        sentinel.prev = target;
        size++;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items from first to last, separated by a space. */
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, return null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            Node target = sentinel.next;
            Node temp = target.next;
            target.next = null;

            sentinel.next = temp;
            target.prev = null;
            temp.prev = sentinel;

            size--;
            return target.item;
        }
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, return null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            Node target = sentinel.prev;
            Node temp = target.prev;
            target.prev = null;

            sentinel.prev = temp;
            target.next = null;
            temp.next = sentinel;

            size--;
            return target.item;
        }
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. If no such item exists, return null.
     *  Must not alter the deque!
     *  Must use iteration, not recursion.
     * @param index the given index
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            Node p = sentinel.next;
            int i = 0;
            while (i < index) {
                p = p.next;
                i++;
            }
            return p.item;
        }
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. If no such item exists, return null.
     *  Must not alter the deque!
     *  Must use recursion, not iteration.
     * @param index the given index
     */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    /** Private helper method for getRecursive with start index.
     * @param p iterative Node flag
     * @param i iterative index flag
     * @return returns the Node at the given index i in the deque
     */
    private T getRecursiveHelper(Node p, int i) {
        if (p == sentinel || i >= size) {
            T t = (T) new Object();
            return t;
        } else if (i == 0) {
            return p.item;
        } else {
            return getRecursiveHelper(p.next, i - 1);
        }
    }
}
