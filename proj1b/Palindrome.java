public class Palindrome {

    /** This method returns a Deque of char from a string. */
    public Deque<Character> wordToDeque(String word) {

        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    /** This method returns true if the word is a palindrome,
     * and false otherwise. */
    public boolean isPalindrome(String word) {
        boolean isPalindrome = true;
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            Character first = d.removeFirst();
            Character last = d.removeLast();
            if (first != last) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    /** This method returns true if the word is a general palindrome,
     *  and false otherwise.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        boolean isPalindrome = true;
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            Character first = d.removeFirst();
            Character last = d.removeLast();
            if (!cc.equalChars(first, last)) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }
}
