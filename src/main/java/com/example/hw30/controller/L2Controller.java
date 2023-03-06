package com.example.hw30.controller;

import com.example.hw30.service.GaussianElimination;
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
public class L2Controller {

    private double[] results = {0d, 0d, 0d, 0d};

    @GetMapping("/l2")
    public String l2() {

        return "l2";
    }

    @PostMapping("/l2")
    public String l1Post(Model model,
                         @RequestParam("a00") double a00, @RequestParam("a01") double a01, @RequestParam("a02") double a02,@RequestParam("a03") double a03,
                         @RequestParam("a10") double a10, @RequestParam("a11") double a11, @RequestParam("a12") double a12,@RequestParam("a13") double a13,
                         @RequestParam("a20") double a20, @RequestParam("a21") double a21, @RequestParam("a22") double a22,@RequestParam("a23") double a23,
                         @RequestParam("a30") double a30, @RequestParam("a31") double a31, @RequestParam("a32") double a32, @RequestParam("a33") double a33,
                         @RequestParam("s00") double s00, @RequestParam("s01") double s01, @RequestParam("s02") double s02, @RequestParam("s03") double s03){


        double[][] matrix = new double[4][4];
        setMatrix( a00,  a01,  a02,  a03,  a10, a11,  a12,  a13, a20,  a21,  a22,  a23, a30, a31,  a32,  a33, matrix);

        double[] sums = new double[4];

        setSolutions(s00, s01, s02,s03, sums);



        model.addAttribute("result1", Math.round(results[0] * 100.0) / 100.0);
        model.addAttribute("result2", Math.round(results[1] * 100.0) / 100.0);
        model.addAttribute("result3", Math.round(results[2] * 100.0) / 100.0);

        printMatr(matrix);
        return "l2";
    }

    private static void setSolutions(double s00, double s01, double s02,  double s03, double[] sums) {
        sums[0] = s00;
        sums[1] = s01;
        sums[2] = s02;
        sums[3] = s03;
    }

    private static void setMatrix(double a00, double a01, double a02, double a03, double a10, double a11, double a12, double a13,double a20, double a21, double a22, double a23,double a30, double a31, double a32, double a33, double[][] matrix) {
        matrix[0][0] = a00;
        matrix[0][1] = a01;
        matrix[0][2] = a02;
        matrix[0][3] = a03;

        matrix[1][0] = a10;
        matrix[1][1] = a11;
        matrix[1][2] = a12;
        matrix[1][3] = a13;

        matrix[2][0] = a20;
        matrix[2][1] = a21;
        matrix[2][2] = a22;
        matrix[2][3] = a23;

        matrix[3][0] = a30;
        matrix[3][1] = a31;
        matrix[3][2] = a32;
        matrix[3][3] = a33;
    }

    public void printMatr(double[][] matrix){
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
}
