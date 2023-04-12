package com.example.hw30.service;

/*
 *
 * @author Roman Netesa
 *
 */
public class FibonachiMethod {

    // Точность решения
    private static final double EPSILON = 0.0001;

    private double ca;
    private double cb;
    private double cc;
    private double cd;
    private double high;
    private double low;


    public FibonachiMethod(double a, double b, double c, double d, double high, double low){
        this.low = low;
        this.high = high;
        this.ca = a;
        this.cb = b;
        this.cc = c;
        this.cd = d;
    }

    // Функция, которую нужно решить
    private double func(double x, double a, double b, double c, double d) {
        return a * x * x * x + b * x * x + c * x + d;
    }

   public  double[] bisection(double a, double b) {
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


    // Метод Фибоначчи для решения уравнения
    public double solve() {
        double[] bounds = bisection(low, high);
        double a = bounds[0];
        double b = bounds[1];
        double x1 = a + fibonacci(bounds.length - 2) / fibonacci(bounds.length) * (b - a);
        double x2 = a + fibonacci(bounds.length - 1) / fibonacci(bounds.length) * (b - a);
        double fx1 = func(x1, ca, cb, cc, cd);
        double fx2 = func(x2, ca, cb, cc, cd);

        for (int i = 0; i < bounds.length - 1; i++) {
            if (fx1 * fx2 > 0) {
                a = x1;
                b = x2;
                x1 = x2;
                x2 = a + fibonacci(bounds.length - i - 1) / fibonacci(bounds.length - i) * (b - a);
                fx1 = fx2;
                fx2 = func(x2, ca, cb, cc, cd);
            } else {
                b = x2;
                x2 = x1;
                x1 = a + fibonacci(bounds.length - i - 2) / fibonacci(bounds.length - i) * (b - a);
                fx2 = fx1;
                fx1 = func(x1, ca, cb, cc, cd);
            }
        }

        return (x1 + x2) / 2;
    }

    // Рекурсивная функция для вычисления чисел Фибоначчи
    private double fibonacci(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

}
