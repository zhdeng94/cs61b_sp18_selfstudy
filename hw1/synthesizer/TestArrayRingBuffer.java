package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnqueue() {
        ArrayRingBuffer<String> testQ = new ArrayRingBuffer<>(3);
        testQ.enqueue("How");
        testQ.enqueue("are");
        testQ.enqueue("you");
        //testQ.enqueue("recently");
    }

    @Test
    public void testDequeue() {
        ArrayRingBuffer<String> testQ = new ArrayRingBuffer<>(3);
        testQ.enqueue("How");
        testQ.enqueue("are");
        testQ.enqueue("you");
        assertEquals(testQ.dequeue(), "How");
        assertEquals(testQ.peek(), "are");
        assertEquals(testQ.dequeue(), "are");
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
