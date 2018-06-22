package com.suichen.plsa;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by suichen on 2015/8/25.
 */

class Posting {
    int docID;
    int pos;

    public Posting(int id, int position) {
        this.docID = id;
        this.pos = position;
    }
}

public class ProbabilisticLSA {
    private DataSet dataSet = null;
    private Posting[][] invertedIndex = null;
    private int M=-1; //number of document
    private int V=-1; //numnber of words
    private int K=-1; // number of topic

    public ProbabilisticLSA() {
    }

    public boolean doPLSA(String datafileName, int ntopics, int iters) {
        File datafile = new File(datafileName);

        if(datafile.exists()) {
            if((this.dataSet = new DataSet(datafile)) == null) {
                return false;
            }
        }

        this.M = this.dataSet.size();
        this.V = this.dataSet.getFeatureNum();
        this.K = ntopics;

        this.buildInvertedIndex(this.dataSet);

        return true;

    }

    private boolean buildInvertedIndex(DataSet ds) {
        ArrayList<Posting>[] list = new ArrayList[this.V];

        for (int m = 0; m < this.M; m++) {
            Data d = ds.getDataAt(m);

            for (int position = 0; position < d.getSize(); position++) {
                int w = d.getFeatureAt(position).dim;
                list[w].add(new Posting(m, position));
            }
        }

        this.invertedIndex = new Posting[this.V][];
        for (int w = 0; w < this.V; w++) {
            this.invertedIndex[w] = list[w].toArray(new Posting[0]);
        }

        return true;
    }

    private boolean init(double[] Pz, double[][] Pd_z, double[][] Pw_z, double[][][] Pz_dw) {
        double zvalue = (double) 1 / (double) this.K;
        for (int z = 0; z < this.K; z++) {
            Pz[z] = zvalue;
        }
        //p(d|z), size: K*M
        for (int z=0; z < this.K;z++) {
            double norm = 0.0;

            for (int m=0; m<this.M;m++) {
                Pd_z[z][m] = Math.random();
                norm += Pd_z[z][m];
            }

            for (int m = 0; m < this.M; m++) {
                Pd_z[z][m] /= norm;
            }
        }

        //p(w|z) size: K*V
        for (int z = 0; z < this.K; z++) {
            double norm = 0.0;
            for (int w = 0; w < this.V; w++) {
                Pw_z[w][z] = Math.random();
                norm += Pw_z[w][z];
            }

            for (int w = 0; w < this.V; w++) {
                Pw_z[w][z] /= norm;
            }
        }

        for (int z = 0; z < this.K; z++) {
            for (int m = 0; m< this.M; m++) {
                Pz_dw[z][m] = new double[this.dataSet.getDataAt(m).getSize()];
            }
        }
        return false;
    }
    private boolean EM(int iters) {
        double[] Pz = new double[this.K]; //p(z), size:K
        double[][] Pd_z = new double[this.K][this.M]; //p(d|z), size:K*M
        double[][] Pw_z = new double[this.K][this.V]; //p(w|z), size:K*V
        double[][][] Pz_dw = new double[this.K][this.M][]; //p(z|d,w), size:K*M*
        double L = -1; // log-likelihood value
        this.init(Pz, Pd_z,Pw_z,Pz_dw);

        return true;
    }

    public boolean Estep(double[] Pz, double[][] Pd_z, double[][] Pw_z, double[][][] Pz_dw) {
        for (int m = 0; m < this.M; m++) {
            Data data = this.dataSet.getDataAt(m);
            for (int position = 0; position < data.getSize(); position++) {
                int w = data.getFeatureAt(position).dim;
                double norm = 0.0;

                for (int z = 0; z < this.K; z++) {
                    double val = Pz[z]*Pd_z[z][m] * Pw_z[z][w]; //我觉得有问题啊
                    Pz_dw[z][m][position] = val;
                    norm += val;
                }

                for (int z = 0; z < this.K; z++) {
                    Pz_dw[z][m][position] /= norm;
                }
            }
        }

        return true;
    }

    private boolean Mstep(double[] Pz, double[][] Pd_z, double[][] Pw_z, double[][][] Pz_dw) {

        for (int z = 0; z < this.K; z++) {//p(w|z)
            double norm = 0.0;

            for (int w = 0; w < this.V; w++) {
                double sum = 0.0;

                Posting[] postings = this.invertedIndex[w];

                for (Posting posting:postings) {
                    int m = posting.docID;
                    int position = posting.pos;

                    double n = this.dataSet.getDataAt(m).getFeatureAt(position).weight;

                    sum += n * Pz_dw[z][m][position];
                }

                Pw_z[z][w] = sum;

                norm += sum;
            }
            //normalization
            for (int w = 0; w < this.V; w++) {
                Pw_z[z][w] /= norm;
            }
        }

        //p(z)

        return true;
    }

    private double calcLoglikelihood(double[] Pz, double[][] Pd_z, double[][] Pw_z) {
        double L = 0.0;

        for (int m = 0; m < this.M; m++) {
            Data d = this.dataSet.getDataAt(m);

            for (int position = 0; position < d.getSize(); position++) {
                Feature f = d.getFeatureAt(position);
                int w = f.dim;
                double n = f.weight;

                double sum = 0.0;

                for (int z = 0; z < this.K; z++) {
                    sum += Pz[z]*Pd_z[z][m]*Pw_z[z][w];
                }

                L += n*Math.log(sum);
            }
        }

        return L;
    }
}
