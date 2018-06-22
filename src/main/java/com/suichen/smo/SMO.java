package com.suichen.smo;

/**
 * Created by suichen on 2015/8/10.
 */
public class SMO {

    private double C = 0.05; //惩罚因子
    private double tolerance = 0.001; //松弛变量
    private double eps = 0.001; //终止条件的差值
    private int[] y = null;
    private SvmNode[][] x=null;
    private int N;
    private double[][] dotCache;
    private double[][] kenerCache;
    private double gamma = 0.08; // exp(-gamma*|u-v|^2)
    private double[] alpha = null;
    private double b=0.0;

    public double dot(SvmNode[] x, SvmNode[] y) {
        double sum = 0.0;
        int cnt = x.length;
        for (int i = 0; i < cnt; i++)
            sum += x[i].getValue()*y[i].getValue();
        return sum;
    }



    public double kernerFuc(int i1, int i2) {
        double sum = 0.0;

        sum = Math.exp(-gamma*(dotCache[i1][i1]+dotCache[i2][i2]-2*dotCache[i1][i2]));

        return sum;
    }

    public void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.dotCache[i][j] = dot(x[i], x[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.kenerCache[i][j] = kernerFuc(i, j);
            }
        }
    }

    public SMO(int[] y, SvmNode[][] x) {
        this.y = y;
        this.x = x;
        this.N = x.length;

        this.alpha = new double[N];
        this.dotCache = new double[N][N];
        this.kenerCache = new double[N][N];

        this.init();
    }

    public double learnFunc(int k) {
        double sum = 0.0;
        int cnt = x[k].length;

        for (int i = 0; i < N; i++) {
            sum += alpha[i]*y[i]*kenerCache[i][k];
        }
        sum += b;
        return sum;
    }

    public double calcError(int k) {
        double error = 0.0;

        error = learnFunc(k)-y[k];

        return error;
    }

    public boolean examineExample(int i1) {
        return true;
    }
    public void train() {
        System.out.println("begin train....");
        int maxIter = 5000;
        int iterCount = 0;
        int numChanged = 0;

        while ((iterCount < maxIter) && (numChanged > 0)) {
            numChanged = 0;

            /*for (int i = 0; i < N; i++) {
                if()
            }*/
        }

    }

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("至少需要一个参数");
            return;
        }

        String file = args[0];

        long start = System.currentTimeMillis();

        SvmData data = FileUtil.loadDataSet(file);

        SMO smo = new SMO(data.getY(), data.getX());
    }
}
