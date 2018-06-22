package com.suichen.smo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by suichen on 2015/8/10.
 */
public class FileUtil {
    private String fileName;

    public FileUtil(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static SvmData loadDataSet(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            ArrayList<SvmDataLine> dataList = new ArrayList<SvmDataLine>();
            int count = 0;
            String buff = null;
            while((buff = reader.readLine()) != null){
                String[] arr = buff.split(" ");
                int yLine = Integer.parseInt(arr[0]);
                ArrayList<SvmNode> nodeList = new ArrayList<SvmNode>();
                for (int i = 1; i < arr.length; i++) {
                    String[] tmp = arr[i].split(":");
                    int index = Integer.parseInt(tmp[0]);
                    double value = Double.parseDouble(tmp[1]);
                    SvmNode node = new SvmNode(index, value);
                    nodeList.add(node);
                }
                SvmNode[] xLine = nodeList.toArray(new SvmNode[]{});
                SvmDataLine dataLine = new SvmDataLine(xLine, yLine);
                dataList.add(dataLine);

                //Ñ¡50¸ö×ö²âÊÔ
                count ++;
                if(count >= 50){
//					break;
                }
            }
            reader.close();

            SvmNode[][] x = new SvmNode[dataList.size()][];
            int[] y = new int[dataList.size()];
            for (int i = 0; i < dataList.size(); i++) {
                SvmDataLine line = dataList.get(i);
                x[i] = line.getX();
                y[i] = line.getY();
            }
            return new SvmData(x, y);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e) {

        }

        return null;
    }
}
