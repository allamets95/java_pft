package lesson1.homework2;

public class Point {

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p1, Point p2) {
        distance(p1, p2);
    }

    public double distance(Point p1, Point p2) {

        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

}
