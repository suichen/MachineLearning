package com.suichen.kmeans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suichen on 2015/7/30.
 */
public class DataElement {
    private Double[] data;
    private int dimesion;


    public DataElement(String[] strings) {
        List<Double> temp = new ArrayList<Double>();

        for (String str:strings) {
            temp.add(Double.parseDouble(str));
        }

        this.data = temp.toArray(new Double[strings.length]);
    }

    public DataElement(Double[] data) {
        this.data = data;
    }

    public int getDimesion() {
        return dimesion;
    }

    public void setDimesion(int dimesion) {
        this.dimesion = dimesion;
    }

    public Double[] getData() {
        return data;
    }

    public void setData(Double[] data) {
        this.data = data;
    }

    public double get(int dimesion) {
        return data[dimesion];
    }
}
