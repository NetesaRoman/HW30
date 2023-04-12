package com.example.hw30.service;

/*
 *
 * @author Roman Netesa
 *
 */

public class DichotomyMethod {

    // Точность решения
    private static final double EPSILON = 0.00001;

    private double ca;
    private double cb;
    private double cc;
    private double cd;


    public DichotomyMethod(double a, double b, double c, double d, double high, double low){

        this.ca = a;
        this.cb = b;
        this.cc = c;
        this.cd = d;
    }

    // Метод дихотомии для определения границ
    public double dichotomy(double a, double b) {
        double[] bounds = new double[2];
        double fa = func(a, ca, cb, cc, cd);
        double fb = func(b, ca, cb, cc, cd);
        double c;

        if (fa * fb > 0) {
            System.out.println("Ошибка: нет корней в данном интервале");
            return -666;
        }

        while (Math.abs(b - a) > EPSILON) {
            c = (a + b) / 2;
            double fc = func(c, ca, cb, cc, cd);

            if (fc == 0) {
                a = c;
                b = c;
            } else if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
        }

        bounds[0] = a;
        bounds[1] = b;

        return (bounds[0] + bounds[1]) / 2;
    }

    // Функция, которую нужно решить
    private double func(double x, double a, double b, double c, double d) {
        return a * x * x * x + b * x * x + c * x + d;
    }


}
