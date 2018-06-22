package com.suichen.kmeans;

import java.util.List;

/**
 * Created by suichen on 2015/7/30.
 */
public class Cluster {
    private List<DataElement> datas;
    private DataElement centroide;

    public List<DataElement> getDatas() {
        return datas;
    }

    public void setDatas(List<DataElement> datas) {
        this.datas = datas;
    }

    public DataElement getCentroide() {
        return centroide;
    }

    public void setCentroide(DataElement centroide) {
        this.centroide = centroide;
    }


}
