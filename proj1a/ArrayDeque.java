/** The array deque is designed as circular.
 * @author 517_JY
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

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Returns true if deque is full, false otherwise. */
    private boolean isFull() {
        return (size == items.length);
    }

    /** Returns true if the deque needs to shrink its size,
     *  false otherwise. */
    private boolean needsShrink() {
        return (4 * size < items.length) && (items.length >= 16);
    }

    /** Deque index addOne circularly.
     *  @param index pending given index
     *  @return next index
     */
    private int addOne(int index) {
        return (index + 1) % items.length;
    }

    /** Deque index minusOne circularly.
     *  @param index pending given index
     *  @return next index
     */
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /** Enlarges the deque. */
    private void enlargeSize() {
        resize(size * 2);
    }

    /** Shrinks the deque. */
    private void shrinkSize() {
        resize(items.length / 2);
    }

    /** Adds an item of type Glorp to the front of the array deque.
     * @param g item g waiting for adding at index nextFirst.
     */
    public void addFirst(T g) {
        if (isFull()) {
            enlargeSize();
        }

        items[nextFirst] = g;
        size++;
        nextFirst = minusOne(nextFirst);
    }

    /** Adds an item of type Glorp to the back of the array deque.
     *  @param g item g waiting for adding at index nextLast.
     */
    public void addLast(T g) {
        if (isFull()) {
            enlargeSize();
        }

        items[nextLast] = g;
        size++;
        nextLast = addOne(nextLast);
    }

    /** Returns the item at the front of the array deque. */
    public T getFirst() {
        int index = addOne(nextFirst);
        return items[index];
    }

    /** Returns the item at the end of the array deque. */
    public T getLast() {
        int index = minusOne(nextLast);
        return items[index];
    }

    /** Returns the item at the given index of the array deque.
     * @param index as specified index
     *  If no such item exists, return null
     *  Needs to flag the first not null item position as the start place.
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int startFlag = addOne(nextFirst);
        int i = (startFlag + index) % items.length;
        return items[i];
    }

    /** Removes and returns the item at the front of the Array deque.
     *  If no such item exists, return null.
     *  Shrink item size to ensure the usage factor always be at least 25%.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = addOne(nextFirst);
        T target = items[nextFirst];
        items[nextFirst]  = null;
        size--;

        if (needsShrink())  {
            shrinkSize();
        }

        return target;
    }


    /** Removes and returns the item at the end of the Array deque.
     *  If no such item exists, return null.
     *  Shrink item size to ensure the usage factor always be at least 25%.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        nextLast = minusOne(nextLast);
        T target = items[nextLast];
        items[nextLast] = null;
        size--;

        if (needsShrink()) {
            shrinkSize();
        }

        return target;
    }

    /** Resize the deque.
     *  @param cap resizing size
     *  Conducts the second copy only when enlarging the deque.
     */
    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        int startflag = nextLast;
        System.arraycopy(items, addOne(nextFirst),
                a, 0, Math.min((items.length - nextFirst - 1), size));

        if (size > items.length - nextFirst - 1) {
            System.arraycopy(items, 0, a,
                    items.length - nextFirst - 1, nextFirst + 1);
        }

        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /** Returns the size of the array deque. */
    public int size() {
        return size;
    }

}
