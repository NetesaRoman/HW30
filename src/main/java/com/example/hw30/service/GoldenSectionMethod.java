package com.example.hw30.service;

/*
 *
 * @author Roman Netesa
 *
 */
public class GoldenSectionMethod {
    // Золотое сечение
    private static final double PHI = (1 + Math.sqrt(5)) / 2;

    // Точность решения
    private static final double EPSILON = 0.00001;

    private double ca;
    private double cb;
    private double cc;
    private double cd;
    private double high;
    private double low;


    public GoldenSectionMethod(double a, double b, double c, double d, double high, double low){
        this.low = low;
        this.high = high;
        this.ca = a;
        this.cb = b;
        this.cc = c;
        this.cd = d;
    }


    // Метод бисекции для определения границ
    private double[] bisection(double a, double b) {
        double[] bounds = new double[2];
        double fa = func(a, ca, cb, cc, cd);
        double fb = func(b, ca, cb, cc, cd);
        double c = (a + b) / 2;
        double fc = func(c, ca, cb, cc, cd);

        if (fa * fb > 0) {
            System.out.println("Ошибка: нет корней в данном интервале");
            return null;
        }

        while (Math.abs(fc) > EPSILON) {
            if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
            c = (a + b) / 2;
            fc = func(c, ca, cb, cc, cd);
        }

        bounds[0] = a;
        bounds[1] = b;

        return bounds;
    }

    // Функция, которую нужно решить
    private double func(double x, double a, double b, double c, double d) {
        return a * x * x * x + b * x * x + c * x + d;
    }

    // Метод золотого сечения для решения уравнения
    public double solve() {
        double[] bounds = bisection(low, high);
        double a = bounds[0];
        double b = bounds[1];
        double x1 = b - (b - a) / PHI;
        double x2 = a + (b - a) / PHI;
        double fx1 = func(x1, ca, cb, cc, cd);
        double fx2 = func(x2, ca, cb, cc, cd);

        while (Math.abs(b - a) > EPSILON) {
            if (fx1 < fx2) {
                b = x2;
                x2 = x1;
                fx2 = fx1;
                x1 = b - (b - a) / PHI;
                fx1 = func(x1, ca, cb, cc, cd);
            } else {
                a = x1;
                x1 = x2;
                fx1 = fx2;
                x2 = a + (b - a) / PHI;
                fx2 = func(x2, ca, cb, cc, cd);
            }
        }

        return (a + b) / 2;
    }
}
