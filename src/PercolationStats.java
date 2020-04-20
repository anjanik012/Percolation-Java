/*
* Created by Anjani Kumar <anjanik012@gmail.com
* 2019
*  */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int trials;
    private final double[] sampleThreshold;
    private static final double CONFIDENCE_95 = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException();

        this.trials = trials;
        this.sampleThreshold = new double[trials];
        int r, c;
        Percolation percolation;

        for (int i = 0; i < this.trials; i++) {
            percolation = new Percolation(n);
            while (!percolation.percolates()) {
                r = StdRandom.uniform(1, n + 1);
                c = StdRandom.uniform(1, n + 1);
                percolation.open(r, c);
            }
            sampleThreshold[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    public static void main(String[] args) {
        Percolation test;
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        if (n <= 0 || t <= 0)
            throw new java.lang.IllegalArgumentException();

        double[] sampleFractions;
        sampleFractions = new double[t];

        int r, c;

        for (int i = 0; i < t; i++) {
            test = new Percolation(n);
            while (!test.percolates()) {
                r = StdRandom.uniform(1, n + 1);
                c = StdRandom.uniform(1, n + 1);
                test.open(r, c);
            }
            sampleFractions[i] = (double) test.numberOfOpenSites() / (n * n);
        }
        double mean = StdStats.mean(sampleFractions);
        double stddev = StdStats.stddev(sampleFractions);
        double lo = mean - CONFIDENCE_95 * stddev / Math.sqrt(t);
        double hi = mean + CONFIDENCE_95 * stddev / Math.sqrt(t);
        System.out.println("mean    =" + mean);
        System.out.println("stddev    =" + stddev);
        System.out.println("95% confidence interval=[" + lo + ", " + hi + "]");
    }

    public double mean() {
        return StdStats.mean(sampleThreshold);
    }

    public double stddev() {
        return StdStats.stddev(sampleThreshold);
    }

    public double confidenceLo() {
        double m = mean();
        double s = stddev();
        return m - CONFIDENCE_95 * s / Math.sqrt(trials);
    }

    public double confidenceHi() {
        double m = mean();
        double s = stddev();
        return m + CONFIDENCE_95 * s / Math.sqrt(trials);
    }


}
