package com.example.hw30.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * @author Roman Netesa
 *
 */
@Controller
@Slf4j
public class L7Controller {

    double x0 = 0; // Початкове значення x
    double y0 = 1; // Початкове значення y
    double h = 0.005; // Крок
    double xMax = 2.1; // Кінцеве значення x
    List<Double> xs = new ArrayList<>();
    List<Double> ye = new ArrayList<>();
    List<Double> yrk = new ArrayList<>();

    @GetMapping("/l7")
    public String l7() {

        return "l7";
    }


    @PostMapping("/l7")
    public String l7Post(Model model) {
        eulerMethod(x0, y0, h, xMax);

        System.out.println();

        rungeKuttaMethod(x0, y0, h, xMax);

        model.addAttribute("x", xs);
        model.addAttribute("Eiler", ye);
        model.addAttribute("Runge", yrk);

        return "l7";
    }





    // Метод Ейлера
    public  void eulerMethod(double x0, double y0, double h, double xMax) {
        double x = x0;
        double y = y0;


        System.out.println("Метод Ейлера:");
        System.out.println("x\ty");

        while (x <= xMax) {
            System.out.println(x + "\t" + y);
            xs.add(x);
            ye.add(y);
            double slope = 0.2 * Math.pow(x, 2) + 0.3 * (Math.pow(x, 3) + y);
            y = y + h * slope;
            x = x + h;
        }
    }

    // Метод Рунге-Кутта
    public  void rungeKuttaMethod(double x0, double y0, double h, double xMax) {
        double x = x0;
        double y = y0;

        System.out.println("Метод Рунге-Кутта:");
        System.out.println("x\ty");

        while (x <= xMax) {
            System.out.println(x + "\t" + y);
            yrk.add(y);
            double k1 = h * (0.2 * Math.pow(x, 2) + 0.3 * (Math.pow(x, 3) + y));
            double k2 = h * (0.2 * Math.pow(x + h/2, 2) + 0.3 * (Math.pow(x + h/2, 3) + y + k1/2));
            double k3 = h * (0.2 * Math.pow(x + h/2, 2) + 0.3 * (Math.pow(x + h/2, 3) + y + k2/2));
            double k4 = h * (0.2 * Math.pow(x + h, 2) + 0.3 * (Math.pow(x + h, 3) + y + k3));

            y = y + (k1 + 2*k2 + 2*k3 + k4) / 6;
            x = x + h;
        }
    }

}
