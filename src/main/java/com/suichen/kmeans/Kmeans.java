package com.suichen.kmeans;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suichen on 2015/7/28.
 */
public class Kmeans {
    public static void main(String[] args) {

        if(args.length != 2) {
            System.out.println("参数个数错误");
            return;
        }

        String trainFile = args[0];
        String testFile = args[1];

        try {
            CSVReader reader = new CSVReader(new FileReader(trainFile));
            List<String[]> myEntries = reader.readAll();
            List<DataElement> matrixData = new ArrayList<DataElement>();//存放矩阵数据

            for (String[] strings:myEntries) {
                DataElement data = new DataElement(strings);
                matrixData.add(data);
            }

            KmeansLearn kmeans = new KmeansLearn();

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
