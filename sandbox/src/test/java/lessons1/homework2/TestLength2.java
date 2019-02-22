package lessons1.homework2;

import lesson1.homework2.Point;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class TestLength2 {

    @Test
    public void testLength() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);

        assertEquals(p1.distance(p2), 0.0);
    }


}