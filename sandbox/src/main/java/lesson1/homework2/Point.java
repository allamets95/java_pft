package lesson1.homework2;

public class Point {

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public double distance(Point p2) {
        x=this.x;
        y=this.y;

        return Math.sqrt((x - p2.x)*(x - p2.x) + (y - p2.y)*(y - p2.y));
    }

}