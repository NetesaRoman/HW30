package com.example.hw30.controller;

import com.example.hw30.service.DichotomyMethod;
import com.example.hw30.service.FibonachiMethod;
import com.example.hw30.service.GoldenSectionMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 *
 * @author Roman Netesa
 *
 */
@Controller
@Slf4j
public class L6Controller {
    double a = 2.0;  // Початкова точка інтервалу
    double b = 4.4;  // Кінцева точка інтервалу
    int n = 10;  // Кількість підрозділів (прямокутників або трапецій)



    @GetMapping("/l6")
    public String l3() {

        return "l6";
    }


    @PostMapping("/l6")
    public String l3Post(Model model, @RequestParam("n") int n) {

        double rectangleApproximation = rectangleMethod(a, b, n);
        double trapezoidApproximation = trapezoidMethod(a, b, n);
        double simpsonApproximation = simpsonMethod(a, b, n);
        
        model.addAttribute("Simpson", simpsonApproximation);
        model.addAttribute("Rectangle", rectangleApproximation);
        model.addAttribute("Trapezoid", trapezoidApproximation);

        return "l6";
    }




    public static double rectangleMethod(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.0;

        for (int i = 0; i < n; i++) {
            double x = a + (i + 0.5) * h;
            sum += function(x);
        }

        return h * sum;
    }

    // Метод трапецій
    public static double trapezoidMethod(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = (function(a) + function(b)) / 2.0;

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += function(x);
        }

        return h * sum;
    }

    // Метод Сімпсона
    public static double simpsonMethod(double a, double b, int n) {
        if (n % 2 != 0) {
            throw new IllegalArgumentException("The number of subintervals must be even.");
        }

        double h = (b - a) / n;
        double sum = function(a) + function(b);

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += (i % 2 == 0) ? 2 * function(x) : 4 * function(x);
        }

        return h * sum / 3.0;
    }

    // Функція, яку інтегруємо
    public static double function(double x) {
        return (x * x - 1.4) / (1.2 * x * x * x + 1);
    }

}
