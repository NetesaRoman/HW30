package com.example.hw30.controller;


import com.example.hw30.service.GaussianElimination;
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
public class MainController {

    private double [] results = {0d, 0d, 0d};




    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/workInProgress")
    public String workInProgress() {
        return "workInProgress";
    }

    @GetMapping("/l1")
    public String l1() {

        return "l1";
    }


    @PostMapping("/l1")
    public String l1Post(Model model,
            @RequestParam("a00") double a00, @RequestParam("a01") double a01, @RequestParam("a02") double a02,
                         @RequestParam("a10") double a10, @RequestParam("a11") double a11, @RequestParam("a12") double a12,
                         @RequestParam("a20") double a20, @RequestParam("a21") double a21, @RequestParam("a22") double a22,
                         @RequestParam("a30") double a30, @RequestParam("a31") double a31, @RequestParam("a32") double a32) {

        GaussianElimination gaussianElimination = new GaussianElimination();
        double[][] matrix = new double[3][3];
        setMatrix(a00, a01, a02, a10, a11, a12, a20, a21, a22, matrix);

        double [] sums = new double[3];

        setSolutions(a30, a31, a32, sums);


       results = gaussianElimination.solve(matrix, sums);
        model.addAttribute("result1", Math.round(results[0] * 100.0) / 100.0);
        model.addAttribute("result2", Math.round(results[1] * 100.0) / 100.0);
        model.addAttribute("result3", Math.round(results[2] * 100.0) / 100.0);
        return "l1";
    }

    private static void setSolutions(double a30, double a31, double a32, double[] sums) {
        sums[0] = a30;
        sums[1] = a31;
        sums[2] = a32;
    }

    private static void setMatrix(double a00, double a01, double a02, double a10, double a11, double a12, double a20, double a21, double a22, double[][] matrix) {
        matrix[0][0] = a00;
        matrix[0][1] = a01;
        matrix[0][2] = a02;

        matrix[1][0] = a10;
        matrix[1][1] = a11;
        matrix[1][2] = a12;

        matrix[2][0] = a20;
        matrix[2][1] = a21;
        matrix[2][2] = a22;
    }
}
