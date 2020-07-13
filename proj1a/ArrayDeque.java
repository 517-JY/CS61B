/** The array deque is designed as circular type. */
public class ArrayDeque<Glorp> {
    private Glorp[] items;
    private int size;

    /** Start the array at 4 -- picked index arbitrarily as described in demo.  */
    private int nextFirst = 4;
    private int nextLast = 5;

    /** Creates an empty array deque with starting size 8. */
    public ArrayDeque() {
        items = (Glorp[]) new Object[8];
        size = 0;
    }

    /** Adds an item of type Glorp to the front of the array deque */
    public void addFirst(Glorp g) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextFirst] = g;
        size++;
        nextFirst--;

        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
    }

    /** Adds an item of type Glorp to the back of the array deque */
    public void addLast(Glorp g) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = g;
        size++;
        nextLast++;

        if (nextLast >= items.length) {
            nextLast = 0;
        }
    }

    /** Returns the item at the front of the array deque */
    public Glorp getFirst() {
        int index = (nextFirst + 1) % items.length;
        return items[index];
    }

    /** Returns the item at the end of the array deque */
    public Glorp getLast() {
        int index = (nextLast - 1) % items.length;
        return items[index];
    }

    /** Returns the item at specified index of the array deque */
    public Glorp get(int index) {
        return items[index];
    }

    /** Removes and returns the item at the front of the Array deque.
     *  If no such item exists, return null.
     *  Shrink item size as needed to ensure the usage factor always be at least 25%.
     */
    public Glorp removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        Glorp target = items[nextFirst];
        items[nextFirst]  = null;
        size--;

        if (4 * size < items.length && items.length >= 16)  {
            shrink();
        }
        return target;
    }


    /** Removes and returns the item at the end of the Array deque.
     *  If no such item exists, return null.
     *  Shrink item size as needed to ensure the usage factor always be at least 25%.
     */
    public Glorp removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = (nextLast - 1) % items.length;
        Glorp target = items[nextLast];
        items[nextLast] = null;
        size--;

        if (4 * size < items.length && items.length >= 16) {
            shrink();
        }

        return target;
    }

    /** Array deque resizing based on requirement */
    private void resize(int cap) {
        Glorp[] a = (Glorp[]) new Object[cap];
        System.arraycopy(items, nextFirst + 1, a, 0, items.length - nextFirst - 1);
        System.arraycopy(items, 0, a, items.length - nextFirst - 1, nextFirst + 1);
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /** Shrinks array deque. */
    private void shrink() {
        Glorp[] a = (Glorp[]) new Object[size * 2];
        System.arraycopy(items, (nextFirst + 1) % items.length, a, 0, size);
        items = a;
        nextLast = size;
        nextFirst = items.length - 1;
    }

    /** Returns the size of the array deque. */
    public int size() {
        return size;
    }

}
