package ru.sqa.pft.sandbox;

public class Point {

    public double getDistance(double x1, double x2, double y1, double y2){

        double result = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        return result;
    }
}
