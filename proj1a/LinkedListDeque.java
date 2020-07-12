import javax.naming.InterruptedNamingException;

public class LinkedListDeque<T> {

    private class Node {
        public Node prev;
        public T item;
        public Node next;

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
    private Node sentFront;
    private Node sentBack;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentFront = new Node(null, null, sentBack);
        sentBack = new Node(sentFront, null, null);
        size = 0;
    }

    /** Creates a linked list deque with an item of type T. */
    public LinkedListDeque(T item) {
        Node mid = new Node(null, item, null);
        sentFront = new Node(null, null, mid);
        sentBack  = new Node(mid, null, null);
        size = 1;
    }

    /** Adds an item of type T to the front of the deque.
     *  All the add and remove operations in this class must not invovle any looping or recursion.
     *  A single such operation must take "constant time"
     *  i.e. execution time should not depend on the size of the deque.
     */
    public void addFirst(T item) {
        sentFront.next = new Node(sentFront, item, sentFront.next);
        size++;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        sentBack.prev = new Node(sentBack.prev, item, sentBack);
        size++;
    }

    /** Returns true if deque is empty, false otherwise.  */
    public boolean isEmpty() {
        return (size==0);
    }

    /** Returns the number of items in the deque.
     *  Must take constant time.
     */
    public int size()
    {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node p = sentFront.next;
        while (p!=null && p!=sentBack) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, return null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node target = sentFront.next;
            sentFront.next = sentFront.next.next;
            size--;
            return target.item;
        }
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, return null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            Node target = sentBack.prev;
            sentBack.prev = sentBack.prev.prev;
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
        if (index >= size){
            return null;
        } else {
            Node p = sentFront;
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
        Node temp = getRecursiveHelper(sentFront, index);
        if (temp == sentFront || temp == sentBack) {
            return null;
        }
        return temp.item;
    }

    private Node getRecursiveHelper(Node p, int i) {
        if (i == 0 || p == sentBack) {
            return p;
        } else {
            return getRecursiveHelper(p.next, i-1);
        }
    }

}