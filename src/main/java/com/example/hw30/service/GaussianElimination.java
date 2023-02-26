package com.example.hw30.service;


/** Class GaussianElimination **/
public class GaussianElimination
{
    public double[] solve(double[][] a, double[] b)
    {
        int n = 3;
        for (int k = 0; k < n; k++)
        {
            /** find pivot row **/
            int max = k;
            for (int i = k + 1; i < n; i++)
                if (Math.abs(a[i][k]) > Math.abs(a[max][k]))
                    max = i;

            /** swap row in A matrix **/
            double[] temp = a[k];
            a[k] = a[max];
            a[max] = temp;

            /** swap corresponding values in constants matrix **/
            double t = b[k];
            b[k] = b[max];
            b[max] = t;

            /** pivot within A and B **/
            for (int i = k + 1; i < n; i++)
            {
                double factor = a[i][k] / a[k][k];
                b[i] -= factor * b[k];
                for (int j = k; j < n; j++)
                    a[i][j] -= factor * a[k][j];
            }
        }

        /** Print row echelon form **/


        /** back substitution **/
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--)
        {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++)
                sum += a[i][j] * solution[j];
            solution[i] = (b[i] - sum) / a[i][i];
        }

        return solution;
    }

    public void printRowEchelonForm(double[][] a, double[] b)
    {
        int n = 3;
        System.out.println("\nRow Echelon form : ");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                System.out.printf("%.2f ", a[i][j]);
            System.out.printf("| %.2f\n", b[i]);
        }
        System.out.println();
    }
    /** function to print solution **/
    public void printSolution(double[] sol)
    {
        int n = 3;
        System.out.println("\nSolution : ");
        for (int i = 0; i < n; i++)
            System.out.printf("%.2f ", sol[i]);
        System.out.println();
    }


}