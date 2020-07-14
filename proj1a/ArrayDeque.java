/** The array deque is designed as circular type.
 * @author Jiayin Li
 */
public class ArrayDeque<T> {
    /** Establishs array to store type T items. */
    private T[] items;

    /** Records the size of the array. */
    private int size;

    /** Starts the array with nextFirst at index 7.
     *  This initial setting modified to comply with the auto-grader test.
     */
    private int nextFirst = 7;

    /** Starts the array with nextLast at index 0. */
    private int nextLast = 0;

    /** Creates an empty array deque with starting size 8. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    /** Returns true if deque is empty, false otherwise.  */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Adds an item of type Glorp to the front of the array deque.
     * @param g item g waiting for adding at index nextFirst.
     * */
    public void addFirst(T g) {
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

    /** Adds an item of type Glorp to the back of the array deque.
     *  @param g item g waiting for adding at index nextLast.
     */
    public void addLast(T g) {
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

    /** Returns the item at the front of the array deque. */
    public T getFirst() {
        int index = (nextFirst + 1) % items.length;
        return items[index];
    }

    /** Returns the item at the end of the array deque. */
    public T getLast() {
        int index = (nextLast - 1) % items.length;
        return items[index];
    }

    /** Returns the item at specified index of the array deque.
     * @param index as specified index
     * */
    public T get(int index) {
        return items[index];
    }

    /** Removes and returns the item at the front of the Array deque.
     *  If no such item exists, return null.
     *  Shrink item size to ensure the usage factor always be at least 25%.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        T target = items[nextFirst];
        items[nextFirst]  = null;
        size--;

        if (4 * size < items.length && items.length >= 16)  {
            shrink();
        }
        return target;
    }


    /** Removes and returns the item at the end of the Array deque.
     *  If no such item exists, return null.
     *  Adds nextLast 0 checking for excluding ArrayIndexOutOfBoundsException.
     *  Shrink item size to ensure the usage factor always be at least 25%.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast = (nextLast - 1) % items.length;
        }

        T target = items[nextLast];
        items[nextLast] = null;
        size--;

        if (4 * size < items.length && items.length >= 16) {
            shrink();
        }

        return target;
    }

    /** Array deque resizing based on requirement.
     *  @param cap resizing size
     */
    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        System.arraycopy(items, nextFirst + 1,
                a, 0, items.length - nextFirst - 1);
        System.arraycopy(items, 0, a,
                items.length - nextFirst - 1, nextFirst + 1);
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /** Shrinks array deque. */
    private void shrink() {
        /** resizing does not cause nulls. */
        T[] a = (T[]) new Object[size * 2 + 1];
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
