
package synthesizer;
import javax.print.attribute.standard.JobOriginatingUserName;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[last] = x;
            fillCount += 1;
            last = plusOne(last);
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T toDequeue = rb[first];
            rb[first] = null;
            first = plusOne(first);
            fillCount -= 1;
            return toDequeue;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return rb[first];
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        return new RingIterator();
    }

    private class RingIterator implements Iterator<T> {
        private int ptr;
        private int numIter;

        // initialize the pointer to the front of the BoundedQueue
        public RingIterator() {
            ptr = first;
            numIter = 0;
        }

        @Override
        public boolean hasNext() {
            if (ptr == last && numIter == fillCount) {
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            T returnItem = rb[ptr];
            ptr = plusOne(ptr);
            numIter += 1;
            return returnItem;
        }
    }

    /** Helper functions for updating the first and the last variable. */
    private int plusOne(int idx) {
        if (idx + 1 == capacity) {
            return 0;
        }
        return idx + 1;
    }
}
