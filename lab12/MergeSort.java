import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> queueToReturn = new Queue<>();
        for (Item i: items) {
            Queue<Item> singleItemQ = new Queue<>();
            singleItemQ.enqueue(i);
            queueToReturn.enqueue(singleItemQ);
        }
        return queueToReturn;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> sortedQ = new Queue<>();
        int totalNum = q1.size() + q2.size();
        for (int i = 0; i < totalNum; i += 1) {
            sortedQ.enqueue(getMin(q1, q2));
        }
        return sortedQ;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        int size = items.size();

        if (size <= 1) {
            return items;
        }
        else if (size == 2) {
            Queue<Queue<Item>> queueWithTwoItem = makeSingleItemQueues(items);
            Queue<Item> q1 = queueWithTwoItem.dequeue();
            Queue<Item> q2 = queueWithTwoItem.dequeue();
            return mergeSortedQueues(q1, q2);
        }
        else {
            Queue<Item> left = new Queue<>();
            for (int i = 0; i < size / 2; i += 1) {
                left.enqueue(items.dequeue());
            }
            Queue<Item> right = items;

            left = mergeSort(left);
            right = mergeSort(right);

            items = mergeSortedQueues(left, right);
            return items;
        }
    }

    /** Test the mergeSort methods. */
    @Test
    public void main() {
        Queue<String> testQ = new Queue<>();
        testQ.enqueue("hello");
        testQ.enqueue("world");
        testQ.enqueue("hi");
        testQ.enqueue("fighting");
        testQ.enqueue("lucky");
        testQ.enqueue("friend");

        System.out.println("Before Sorting");
        for (String s: testQ) {
            System.out.print(s + " ");
        }
        System.out.println('\n');

        Queue<String> sortedQ = mergeSort(testQ);
        System.out.println("After Sorting");
        for (String s: sortedQ) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
