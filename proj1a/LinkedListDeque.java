public class LinkedListDeque<T> {

    public class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /** The first item (if it exists) is at sentFront.next
     *  The last item (if it exists) is at sentBack.prev
     */
    private int size;
    private Node sentinel;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        T t = (T) new Object();
        sentinel = new Node(sentinel, t, sentinel);
        size = 0;
    }

    /** Creates a linked list deque with an item of type T. */
    public LinkedListDeque(T item) {
        T t = (T) new Object();
        sentinel = new Node(sentinel, t, sentinel);
        Node target = new Node(sentinel, item, sentinel);
        sentinel.next = target;
        sentinel.prev = target;
        size = 1;
    }

    /** Adds an item of type T to the front of the deque.
     *  All the add and remove operations in this class must not invovle any looping or recursion.
     *  A single such operation must take "constant time"
     *  i.e. execution time should not depend on the size of the deque.
     */
    public void addFirst(T item) {

        sentinel.next = new Node(sentinel, item, sentinel.next);
        size++;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {

        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        size++;
    }

    /** Returns true if deque is empty, false otherwise.  */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Returns the number of items in the deque.
     *  Must take constant time.
     */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel && p != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, return null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node target = sentinel.next;
            sentinel.next = sentinel.next.next;
            size--;
            return target.item;
        }
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, return null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            Node target = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            size--;
            return target.item;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, return null.
     *  Must not alter the deque!
     *  Must use iteration, not recursion.
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            Node p = sentinel;
            int i = 0;
            while (i < index) {
                p = p.next;
                i++;
            }
            return p.item;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, return null.
     *  Must not alter the deque!
     *  Must use recursion, not iteration.
     */
    public T getRecursive(int index) {
        Node temp = getRecursiveHelper(sentinel, index);
        if (temp == sentinel) {
            return null;
        }
        return temp.item;
    }

    private Node getRecursiveHelper(Node p, int i) {
        if (i == 0 || p == sentinel) {
            return p;
        } else {
            return getRecursiveHelper(p.next, i - 1);
        }
    }

}
