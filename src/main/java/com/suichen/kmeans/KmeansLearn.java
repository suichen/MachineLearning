package com.suichen.kmeans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suichen on 2015/7/30.
 */
public class KmeansLearn {

    public void calcular(List<DataElement> matrixData, int k) {
        List<Cluster> clusters = elegirCentroides(matrixData, k);
    }

    public List<Cluster> elegirCentroides(List<DataElement> matrixData, int k) {

        List<Double> maxDimesion = new ArrayList<Double>();
        List<Double> minDimesion = new ArrayList<Double>();

        int dimesion = matrixData.get(0).getData().length;

        for (int i = 0; i < dimesion; i++) {
            double minn = Double.POSITIVE_INFINITY, maxx = Double.NEGATIVE_INFINITY;
            for (DataElement data:matrixData) {
                maxx = data.get(i) > maxx ? data.get(i) : maxx;
                minn = data.get(i) < minn ? data.get(i) : minn;
            }
            maxDimesion.add(maxx);
            minDimesion.add(minn);
        }

        List<Cluster> clusters = new ArrayList<Cluster>();
        return null;
    }
}
