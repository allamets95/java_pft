package ru.sqa.pft.sandbox;

public class test {

    public static void main(String[] args) {

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + area(s));

        Rectangle r = new Rectangle(4,5);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));

        Point obj1 = new Point(5,4);
        Point obj2 = new Point(6,10);
        System.out.println("Distance: " + "A(" + obj1.x + ":" + obj1.y + ") B(" + obj2.x + ":" + obj2.y + ")" + "=" + point(obj1, obj2));
    }

    public static double point(Point obj1, Point obj2){
        //Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double distance = Math.sqrt((obj2.x-obj1.x)*(obj2.x-obj1.x)+(obj2.y-obj1.y)*(obj2.y-obj1.y));
        return distance;
    }

    public static  double area(Square s){
        return s.l * s.l;
    }

    public static  double area(Rectangle r){
        return r.a * r.b;
    }

}