package lessons1.homework2;

import lesson1.homework2.Point;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class TestLength4 {

    @Test
    public void testLength() {
        Point p1 = new Point(-2, 2);
        Point p2 = new Point(4, -4);

        assertEquals(p1.distance(p2), 8.48528137423857);
    }


}