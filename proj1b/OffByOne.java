public class OffByOne implements CharacterComparator {

    /** This method returns true if two char differ by one,
     *  and false otherwise.
     */
    @Override
    public boolean equalChars(char a, char b) {
        if (a - b == 1 || a - b == -1) {
            return true;
        } else {
            return false;
        }
    }

}
