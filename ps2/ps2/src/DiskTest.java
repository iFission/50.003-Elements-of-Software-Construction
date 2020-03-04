
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class DiskTest {

    public int x, y;

    public DiskTest(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{{4, 12}
        });
    }

    @Test
    public void manipulateTest() {
        Disk disk = new Disk(x, y);
        disk.manipulate();
    }
}