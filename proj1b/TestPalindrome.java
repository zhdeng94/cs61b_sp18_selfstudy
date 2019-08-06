import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("A"));
        assertFalse(palindrome.isPalindrome("Sun"));
        assertFalse(palindrome.isPalindrome("Apa"));
        assertTrue(palindrome.isPalindrome("noon"));
    }

    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testIsPalindromeOffByOne() {
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("A", offByOne));
        assertFalse(palindrome.isPalindrome("Sunny", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
    }
}
