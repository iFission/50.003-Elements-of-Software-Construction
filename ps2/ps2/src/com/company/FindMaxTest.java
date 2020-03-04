import org.junit.Test;

import static org.junit.Assert.*;

public class FindMaxTest {
    @Test
    public void testSuccess() {
        int[] intArray = new int[] { 5, 6, 17, 8, 2 };
        assertTrue(FindMax.max(intArray) == 17);
    }

    @Test(expected = Exception.class)
    public void testException() {
        int[] intArray = new int[0];
        FindMax.max(intArray);
    }

    @Test
    public void testFailure() {
        int[] intArray = new int[] { 1, 2, 3 };
        assertEquals(FindMax.max(intArray), 3);
    }
}
