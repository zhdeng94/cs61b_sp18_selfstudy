public class ArrayDeque<T> implements Deque<T> {
    /** This is the class definition of a circular array deque. */

    /** Member variables */
    private int size; // current size of the array
    private T[] array; // an array of generic type T
    private int nextFirst; // index of the first element after addFirst to array
    private int nextLast; // index of the last element after addLast to array

    /** Member functions */

    /** Constructor */
    public ArrayDeque() {
        size = 0;
        array = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        if (size >= array.length) {
            resize(size * 2);
            array[nextFirst] = item;
            nextFirst = minusOne(nextFirst);
        } else {
            array[nextFirst] = item;
            nextFirst = minusOne(nextFirst);
        }
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        if (size >= array.length) {
            resize(size * 2);
            array[nextLast] = item;
            nextLast = plusOne(nextLast);
        } else {
            array[nextLast] = item;
            nextLast = plusOne(nextLast);
        }
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        int position = plusOne(nextFirst);
        for (int i = 0; i < size; i += 1) {
            System.out.print(array[position]);
            System.out.print(' ');
            position = plusOne(position);
        }
        System.out.print('\n');
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null. */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        int position = plusOne(nextFirst);
        T itemToReturn = array[position];
        array[position] = null;
        nextFirst = position;
        size -= 1;

        if ((double)size / array.length < 0.25 && array.length >= 16) {
            resize(array.length / 2);
        }
        return itemToReturn;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        int position = minusOne(nextLast);
        T itemToReturn = array[position];
        array[position] = null;
        nextLast = position;
        size -= 1;

        if ((double)size / array.length < 0.25 && array.length >= 16) {
            resize(array.length / 2);
        }
        return itemToReturn;
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth.
     *  If no such item exists, returns null. */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int position = index + plusOne(nextFirst);
        if (position >= array.length) {
            position -= array.length;
            return array[position];
        } else {
            return array[position];
        }
    }

    /** Helper function to compute the index immediately after an index. */
    private int plusOne(int index) {
        if (index + 1 >= array.length) {
            return 0;
        } else {
            return index + 1;
        }
    }

    /** Helper function to compute the index immediately before an index. */
    private int minusOne(int index) {
        if (index - 1 < 0) {
            return array.length - 1;
        } else {
            return index - 1;
        }
    }

    /** Helper function to resize the array to appropriate size */
    private void resize(int capacity) {
        // create a new array, and copy the original array items to the new array
        T[] newArray = (T[]) new Object[capacity];
        int position = plusOne(nextFirst);
        for (int i = 0; i < size; i += 1) {
            newArray[i] = array[position];
            position = plusOne(position);
        }
        nextFirst = newArray.length - 1;
        nextLast = size;
        array = newArray;
    }
}
