public class LinkedListDeque<T> {
    /** This is the class definition of a double-ended queue. */


    /** Deque member variables */
    private Node sentinel;
    private int size;

    /** Node is the building block of the Deque. */
    public class Node {
        public Node prev;
        public T item;
        public Node next;

        /** Default constructor */
        public Node() {
            prev = null;
            item = null;
            next = null;
        }

        /** Value constructor */
        public Node(Node n1, T t, Node n2) {
            prev = n1;
            item = t;
            next = n2;
        }
    }

    /** Deque member functions. */

    /** Constructor */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /** Add an item of type T to the start of the Deque. */
    public void addFirst(T item) {
        Node insertNode = new Node(sentinel, item, sentinel.next);
        insertNode.prev.next = insertNode;
        insertNode.next.prev = insertNode;
        size += 1;
    }

    /** Add an item of Type T to the end of the Deque. */
    public void addLast(T item) {
        Node insertNode = new Node(sentinel.prev, item, sentinel);
        insertNode.prev.next = insertNode;
        insertNode.next.prev = insertNode;
        size += 1;
    }

    /** Return true if the Deque is empty, and false otherwise */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Return the number of items in the Deque */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node position = sentinel.next;
        for (int i = 0; i < size; i += 1) {
            System.out.print(position.item);
            System.out.print(' ');
            position = position.next;
        }
        System.out.print('\n');
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node removeNode = sentinel.next;
        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;
        removeNode.prev = null;
        removeNode.next = null;
        size -= 1;
        return removeNode.item;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node removeNode = sentinel.prev;
        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;
        removeNode.prev = null;
        removeNode.next = null;
        size -= 1;
        return removeNode.item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null. */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node itemToGet = sentinel.next;
        for (int i = 0; i < index; i += 1) {
            itemToGet = itemToGet.next;
        }
        return itemToGet.item;
    }

    /** Same functionality as get(int index) method, but use recursion instead of iteration */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    /** Helper function for getRecursive method */
    private T getRecursive(int index, Node nodePosition) {
        if (index == 0) {
            return nodePosition.item;
        }
        return getRecursive(index - 1, nodePosition.next);
    }

}
