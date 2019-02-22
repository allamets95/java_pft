package lessons1.homework2;

        import lesson1.homework2.Point;
        import org.testng.Assert;
        import org.testng.annotations.Test;

        import static org.testng.Assert.*;



public class TestLength1 {

    @Test
    public void testLength() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(4, 4);

        assertEquals(p1.distance(p2), 2.8284271247461903);
    }


}
