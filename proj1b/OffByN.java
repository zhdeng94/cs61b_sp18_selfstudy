public class OffByN implements CharacterComparator {

    private int offset;

    public OffByN(int N) {
        offset = N;
    }

    /** This method returns true if two char differ by N,
     *  and false otherwise.
     */
    @Override
    public boolean equalChars(char a, char b) {
        if (a - b == offset || a - b == -offset) {
            return true;
        }
        return false;
    }
}
