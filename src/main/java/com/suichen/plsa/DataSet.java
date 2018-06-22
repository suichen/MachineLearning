package com.suichen.plsa;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by suichen on 2015/8/25.
 */
public class DataSet {
    private ArrayList<Data> datas;
    private int dataNum = -1;
    private int featureNum = -1;

    public void refreshStatistics() {
        this.dataNum = this.datas.size();
        HashSet<Integer> calculator = new HashSet<Integer>();

        for (Data d: datas) {
            for (Feature f:d.getAllFeature()) {
                calculator.add(f.dim);
            }
        }

        this.featureNum = calculator.size();
    }

    public DataSet() {
        this.datas = new ArrayList<Data>();
        refreshStatistics();
    }

    public DataSet(ArrayList<Data> initDatas) {
        this.datas = initDatas;
        refreshStatistics();
    }

    public DataSet(File datafile) {
        this.datas = new ArrayList<Data>();

        try {
            List<String> datalines = FileUtils.readLines(datafile);
            for (int i = 0; i < datalines.size(); i++) {
                datas.add(new Data(i, datalines.get(i)));
            }
            refreshStatistics();
        }catch (IOException e) {

        }
    }
    public Data getDataAt(int index){
        return datas.get(index);
    }

    public ArrayList<Data> getAllData() {
        return datas;
    }

    public int size() {
        return this.dataNum;
    }

    public int getFeatureNum() {
        return this.featureNum;
    }
}
