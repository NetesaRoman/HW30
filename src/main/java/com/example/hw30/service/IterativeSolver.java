package com.example.hw30.service;

/*
 *
 * @author Roman Netesa
 *
 */
public class IterativeSolver {
    private double[][] A = {{2, 1, 1, 1}, {1, 2, 1, 1}, {1, 1, 2, 1}, {1, 1, 1, 2}}; // матрица коэффициентов
    private double[] b = {3, 3, 3, 3}; // вектор правой части
    private double[] x0 = {0, 0, 0, 0}; // начальное приближение
    private double epsilon = 0.0001; // заданная точность
    private int maxIterations = 1000; // максимальное количество итераций

    public IterativeSolver(double[][] matrix, double[] x0, double e){
        for (int i = 0;i < 4 ;i++){
            for (int j = 0;j < 4 ;j++){
                A[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < 4; i++){
            b[i] = matrix[i][4];
            this.x0[i] = x0[i];
        }

        this.epsilon = e;
    }

    public double[] solve() {


        // вычисление матрицы перехода
        double[][] t = new double[4][4];
        double[] d = new double[4];
        for (int i = 0; i < 4; i++) {
            d[i] = A[i][i];
            for (int j = 0; j < 4; j++) {
                t[i][j] = (i == j) ? 0 : -A[i][j] / d[i];
            }
        }

        // вычисление вектора смещения
        double[] c = new double[4];
        for (int i = 0; i < 4; i++) {
            c[i] = b[i] / d[i];
        }

        // итерационный процесс
        double[] x = x0;
        int iterations = 0;
        while (iterations < maxIterations) {
            double[] xNew = new double[4];
            for (int i = 0; i < 4; i++) {
                xNew[i] = 0;
                for (int j = 0; j < 4; j++) {
                    xNew[i] += t[i][j] * x[j];
                }
                xNew[i] += c[i];
            }
            if (converged(x, xNew, epsilon)) {
                break;
            }
            x = xNew;
            iterations++;
        }

        // вывод результата
        System.out.println("Solution:");
        for (int i = 0; i < 4; i++) {
            System.out.println("x[" + i + "] = " + x[i]);
        }

        return x;
    }

    // проверка на достижение заданной точности
    private  boolean converged(double[] x, double[] xNew, double epsilon) {
        for (int i = 0; i < 4; i++) {
            if (Math.abs(xNew[i] - x[i]) > epsilon) {
                return false;
            }
        }
        return true;
    }

    public void printInfo(){
        for(int i = 0; i < 4 ; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(A[i][j] + " ");

            }
            System.out.println("\n");
        }
        System.out.println("-----------");
        for(int i = 0; i < 4 ; i++){
            System.out.print(b[i] + " ");
        }

        System.out.println("x0----------");
        for(int i = 0; i < 4 ; i++){
            System.out.print(x0[i] + " ");
        }
    }
}