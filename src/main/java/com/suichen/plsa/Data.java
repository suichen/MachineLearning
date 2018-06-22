package com.suichen.plsa;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by suichen on 2015/8/25.
 */
public class Data {
    private int id = -1;
    private ArrayList<Feature> features = null;
    private int label = Integer.MIN_VALUE;
    private int size = -1;

    public Data(int initID, String line) {
        this.id = initID;
        this.features = readData(line);
        this.size = this.features.size();
    }

    private ArrayList<Feature> readData(String line) {
        StringTokenizer stk = new StringTokenizer(line);

        try {
            this.label = Integer.parseInt(stk.nextToken());
            ArrayList<Feature> fs = new ArrayList<Feature>();
            while (stk.hasMoreTokens()) {
                String pair[] = stk.nextToken().split(":");
                int dim = Integer.parseInt(pair[0]);
                double value = Double.parseDouble(pair[1]);

                fs.add(new Feature(dim,value));
            }

            return fs;
        }catch (NumberFormatException nfe) {
            ErrorReport.showMessage(this,"readData");
            return null;
        }
    }

    public int getID() {
        return this.id;
    }

    public Feature getFeatureAt(int position) {
        return features.get(position);
    }

    public int getLabel() {
        return this.label;
    }

    public ArrayList<Feature> getAllFeature() {
        return features;
    }

    public int getSize() {
        return this.size;
    }
}
