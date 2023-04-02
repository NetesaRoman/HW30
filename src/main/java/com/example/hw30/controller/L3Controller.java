package com.example.hw30.controller;


import com.example.hw30.service.DichotomyMethod;
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
                         @RequestParam("step") double step, @RequestParam("high") double high, @RequestParam("low") double low){

    double[] coefficients = {a,b,c,d};
        PolynomialFunction polynomialFunction= new PolynomialFunction(coefficients);
       model.addAttribute("result", DichotomyMethod.count(polynomialFunction, high, low));


        return "l3";
    }

}
