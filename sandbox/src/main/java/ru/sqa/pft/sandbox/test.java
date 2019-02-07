package ru.sqa.pft.sandbox;

public class test {

    public static void main(String[] args) {

        NewSquare s = new NewSquare(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area(s));

        Rectangle r = new Rectangle(4,5);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area(r));
    }

    public static  double area(NewSquare s){
        return s.l * s.l;
    }

}