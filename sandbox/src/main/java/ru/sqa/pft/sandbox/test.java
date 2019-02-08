package ru.sqa.pft.sandbox;

public class test {

    public static void main(String[] args) {

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + area(s));

        Rectangle r = new Rectangle(4,5);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));

        Point obj = new Point();
        double pointResult = obj.getDistance(5.0,2.2,7.0,3.0);
        System.out.println("Distance between points:" + pointResult);
        //System.out.println("Distance: " + "A(" + obj1.x + ":" + obj1.y + ") B(" + obj2.x + ":" + obj2.y + ")" + "=" + point(obj1, obj2));
    }

    public static  double area(Square s){
        return s.l * s.l;
    }

    public static  double area(Rectangle r){
        return r.a * r.b;
    }

}