package com.suichen.smo;

/**
 * Created by suichen on 2015/8/10.
 */
public class SvmNode {
    private int index;
    private double value;

    public SvmNode(int index, double value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
