package com.suichen.test;

/**
 * Created by suichen on 2015/7/31.
 */
public class Test01 {

    public static int G1(int i) {
        if(i < 2.5) return 1;
        return -1;
    }

    public static void main(String[] args) {
        int[] ab={1,1,1,-1,-1,-1,1,1,1,-1};
        double[] w=new double[ab.length];

        double a1=0.4236;

        for (int i = 0; i < ab.length; i++) {
            w[i] = 1.0/ab.length;
        }

        double z1=0.0;

        for (int i = 0; i < w.length; i++) {
            z1 += w[i]*Math.exp(-a1*G1(i)*ab[i]);
        }

        for (int i = 0; i < w.length; i++) {
            w[i] = w[i]*Math.exp(-a1*G1(i)*ab[i])/z1;
        }

        for (int i = 0; i < ab.length; i++) {
            System.out.printf("%.5f\n", w[i]);
        }
    }
}
