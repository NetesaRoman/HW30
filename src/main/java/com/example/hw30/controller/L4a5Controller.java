package com.example.hw30.controller;


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
public class L4a5Controller {

    @GetMapping("/l4and5")
    public String l3() {

        return "l4and5";
    }


    @PostMapping("/l4and5")
    public String l3Post(Model model, @RequestParam("march") double march, @RequestParam("april") double april, @RequestParam("may") double may, @RequestParam("june") double june,
                         @RequestParam("july") double july) {

        double[] x = {1d,2d,3d,4d,5d};
        double[] y = {march, april,may, june, july};
        double[] result ={0,0,0,0,0};
        for (int i = 0; i<5; i++){
            result[i] = lagrangeInterpolation(x, y, x[i] - 1);
        }

        double[] firstDegreeCoefficients = getFirstDegreeCoefficients(x, y);
        double[] secondDegreeCoefficients = getSecondDegreeCoefficients(x, y);

        double firstDegreeError = getError(x, y, firstDegreeCoefficients, 1);
        double secondDegreeError = getError(x, y, secondDegreeCoefficients, 2);

        System.out.println("Coefficients of the first degree polynomial: ");
        for (double coefficient : firstDegreeCoefficients) {
            System.out.print(coefficient + " ");
        }
        model.addAttribute("f1", firstDegreeCoefficients[0]);
        model.addAttribute("f2", firstDegreeCoefficients[1]);
        model.addAttribute("ferr", firstDegreeError);
        System.out.println("\nError of the first degree polynomial: " + firstDegreeError);

        System.out.println("\nCoefficients of the second degree polynomial: ");
        for (double coefficient : secondDegreeCoefficients) {
            System.out.print(coefficient + " ");
        }
        model.addAttribute("s1", secondDegreeCoefficients[0]);
        model.addAttribute("s2", secondDegreeCoefficients[1]);
        model.addAttribute("s3", secondDegreeCoefficients[2]);
        model.addAttribute("serr", secondDegreeError);
        System.out.println("\nError of the second degree polynomial: " + secondDegreeError);

        if (firstDegreeError < secondDegreeError) {
            model.addAttribute("resultBetter", "First degree polynomial is a better approximation.");
            System.out.println("\nFirst degree polynomial is a better approximation.");
        } else {
            model.addAttribute("resultBetter", "Second degree polynomial is a better approximation.");
            System.out.println("\nSecond degree polynomial is a better approximation.");
        }




        model.addAttribute("result1",result[0]);
        model.addAttribute("result2",result[1]);
        model.addAttribute("result3",result[2]);
        model.addAttribute("result4",result[3]);
        model.addAttribute("result5",result[4]);
        return "l4and5";
    }

    public static double[] getFirstDegreeCoefficients(double[] x, double[] y) {
        double[] coefficients = new double[2];
        double sumX = 0.0, sumY = 0.0, sumXY = 0.0, sumXX = 0.0;
        int n = x.length;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumXX += x[i] * x[i];
        }

        double denominator = n * sumXX - sumX * sumX;
        coefficients[0] = (n * sumXY - sumX * sumY) / denominator;
        coefficients[1] = (sumXX * sumY - sumX * sumXY) / denominator;

        return coefficients;
    }

    public static double[] getSecondDegreeCoefficients(double[] x, double[] y) {
        double[] coefficients = new double[5];
        double sumX = 0.0, sumY = 0.0, sumXY = 0.0, sumXX = 0.0, sumXXX = 0.0, sumXXXX = 0.0, sumXXY = 0.0;
        int n = x.length;

        for (int i = 0; i < n; i++) {
            sumX += x[i];

            sumY += y[i];
            sumXY += x[i] * y[i];
            sumXX += x[i] * x[i];
            sumXXX += x[i] * x[i] * x[i];
            sumXXXX += x[i] * x[i] * x[i] * x[i];
            sumXXY += x[i] * x[i] * y[i];
        }

        double[][] matrix = {{n, sumX, sumXX}, {sumX, sumXX, sumXXX}, {sumXX, sumXXX, sumXXXX}};
        double[] constants = {sumY, sumXY, sumXXY};
        coefficients = solveLinearSystem(matrix, constants);

        return coefficients;
    }

    public static double[] solveLinearSystem(double[][] matrix, double[] constants) {
        int n = constants.length;

        for (int k = 0; k < n; k++) {
            int max = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(matrix[i][k]) > Math.abs(matrix[max][k])) {
                    max = i;
                }
            }

            double[] temp = matrix[k];
            matrix[k] = matrix[max];
            matrix[max] = temp;
            double t = constants[k];
            constants[k] = constants[max];
            constants[max] = t;

            for (int i = k + 1; i < n; i++) {
                double factor = matrix[i][k] / matrix[k][k];
                constants[i] -= factor * constants[k];
                for (int j = k; j < n; j++) {
                    matrix[i][j] -= factor * matrix[k][j];
                }
            }
        }

        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += matrix[i][j] * solution[j];
            }
            solution[i] = (constants[i] - sum) / matrix[i][i];
        }

        return solution;
    }

    public static double getError(double[] x, double[] y, double[] coefficients, int degree) {
        double error = 0.0;

        for (int i = 0; i < x.length; i++) {
            double predictedY = 0.0;
            for (int j = 0; j <= degree; j++) {
                predictedY += coefficients[j] * Math.pow(x[i], j);
            }
            error += Math.pow(predictedY - y[i], 2);
        }

        return error;
    }


    public static double lagrangeInterpolation(double[] x, double[] y, double inputX) {
        double result = 0.0;
        for (int i = 0; i < x.length; i++) {
            double term = y[i];
            for (int j = 0; j < x.length; j++) {
                if (i != j) {
                    term = term * (inputX - x[j]) / (x[i] - x[j]);
                }
            }
            result += term;
        }
        return result;
    }
}


