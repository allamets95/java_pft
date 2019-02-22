package lesson1.homework2;

import lesson1.homework2.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Distance {

    public static void main(String[] args) throws IOException {


        int p1x = 10;
        int p1y = 20;
        Point p1 = new Point(p1x, p1y);
        int p2x = 2;
        int p2y = 78;
        Point p2 = new Point(p2x, p2y);


        System.out.println("---------Пример---------");
        System.out.println("Расстояние между точками P1("+p1x+","+p1y+"), P2("+p2x+","+p2y+"): "+ p1.distance(p2));

        p1x = 0;
        p1y = -20;
        p1 = new Point(p1x, p1y);
        p2x = 25;
        p2y = -7;
        p2 = new Point(p2x, p2y);


        System.out.println("---------Пример----------");
        System.out.println("Расстояние между точками P1("+p1x+","+p1y+"), P2("+p2x+","+p2y+"): "+ p1.distance(p2));

        p1x = 0;
        p1y = -20;
        p1 = new Point(p1x, p1y);
        p2x = 25;
        p2y = -7;
        p2 = new Point(p2x, p2y);


        System.out.println("---------Пример----------");
        System.out.println("Расстояние между точками P1("+p1x+","+p1y+"), P2("+p2x+","+p2y+"): "+p1.distance(p2));

        p1x = -10;
        p1y = -20;
        p1 = new Point(p1x, p1y);
        p2x = -55;
        p2y = -7;
        p2 = new Point(p2x, p2y);


        System.out.println("---------Пример----------");
        System.out.println("Расстояние между точками P1("+p1x+","+p1y+"), P2("+p2x+","+p2y+"): "+ p1.distance(p2));

        p1x = 0;
        p1y = 0;
        p1 = new Point(p1x, p1y);
        p2x = 0;
        p2y = 0;
        p2 = new Point(p2x, p2y);


        System.out.println("---------Пример----------");
        System.out.println("Расстояние между точками P1("+p1x+","+p1y+"), P2("+p2x+","+p2y+"): "+ p1.distance(p2));

        p1x = 10;
        p1y = 20;
        p1 = new Point(p1x, p1y);
        p2x = 0;
        p2y = 0;
        p2 = new Point(p2x, p2y);


        System.out.println("---------Пример----------");
        System.out.println("Расстояние между точками P1("+p1x+","+p1y+"), P2("+p2x+","+p2y+"): "+ p1.distance(p2));

        p1x = 0;
        p1y = 0;
        p1 = new Point(p1x, p1y);
        p2x = 10;
        p2y = 20;
        p2 = new Point(p2x, p2y);


        System.out.println("---------Пример----------");
        System.out.println("Расстояние между точками P1("+p1x+","+p1y+"), P2("+p2x+","+p2y+"): "+ p1.distance(p2));


      }


}