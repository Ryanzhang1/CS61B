import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testoffByOne() {
        CharacterComparator obo = new OffByOne();
        assertTrue(obo.equalChars('a', 'b'));
        assertTrue(obo.equalChars('r', 'q'));
        assertTrue(obo.equalChars('&', '%'));

        assertFalse(obo.equalChars('a', 'a'));
        assertFalse(obo.equalChars('a', 'z'));
        assertFalse(obo.equalChars('a', 'e'));
        assertFalse(obo.equalChars('a', 'A'));

    }
}

// Your tests go here.

