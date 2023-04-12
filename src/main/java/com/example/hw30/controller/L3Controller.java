package com.example.hw30.controller;


import com.example.hw30.service.DichotomyMethod;
import com.example.hw30.service.FibonachiMethod;
import com.example.hw30.service.GoldenSectionMethod;
import com.example.hw30.service.IterativeSolver;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
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
public class L3Controller {


    @GetMapping("/l3")
    public String l3() {

        return "l3";
    }


    @PostMapping("/l3")
    public String l3Post(Model model,
                         @RequestParam("a") double a, @RequestParam("b") double b, @RequestParam("c") double c, @RequestParam("d") double d,
                         @RequestParam("high") double high, @RequestParam("low") double low) {


        DichotomyMethod dichotomyMethod = new DichotomyMethod(a,b,c,d,high,low);
        model.addAttribute("result_dichtomy", dichotomyMethod.dichotomy(low, high));

        FibonachiMethod fibonachiMethod = new FibonachiMethod(a,b,c,d,high,low);

        model.addAttribute("result_fibo", fibonachiMethod.solve());

        GoldenSectionMethod goldenSectionMethod = new GoldenSectionMethod(a,b,c,d,high,low);
        model.addAttribute("result_golden", goldenSectionMethod.solve());
        return "l3";
    }

}
