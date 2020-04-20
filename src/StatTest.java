/*
* Created by Anjani Kumar <anjanik012@gmail.com>
* 2019
* */

public class StatTest {

    public static void main(String[] args){
        PercolationStats stats = new PercolationStats(200, 100);
        double mean = stats.mean();
        double stddedv = stats.stddev();
        double lo = stats.confidenceLo();
        double hi = stats.confidenceHi();

        System.out.printf("mean=%f, stddev=%f, lo=%f, hi=%f\n", mean, stddedv, lo, hi);
    }

}
