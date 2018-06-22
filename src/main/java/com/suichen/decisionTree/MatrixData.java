package com.suichen.decisionTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by suichen on 2015/7/27.
 */
public class MatrixData {
    private ArrayList<int[]> data;
    private ArrayList<String> header;
    private int rowNums;
    private int columnNums;

    public MatrixData() {
        data = new ArrayList<int[]>();
        header = new ArrayList<String>();
    }


    public ArrayList<int[]> getData() {
        return data;
    }

    public void setData(ArrayList<int[]> data) {
        this.data = data;
    }

    public ArrayList<String> getHeader() {
        return header;
    }

    public void setHeader(ArrayList<String> header) {
        this.header = header;
    }

    public int getRowNums() {
        return rowNums;
    }

    public void setRowNums(int rowNums) {
        this.rowNums = rowNums;
    }

    public int getColumnNums() {
        return columnNums;
    }

    public void setColumnNums(int columnNums) {
        this.columnNums = columnNums;
    }

    public void fillMatrix(int[] vector, int tmpIndex) {
        int currIndex = 0;

        for (int[] row:data) {
            vector[currIndex++] = row[tmpIndex];
        }

    }

    //´æ´¢Êý¾Ý
    public void preadyData(String fileName) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(fileName));

            {
                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line);

                boolean isNum = true;

                while (st.hasMoreElements()) {
                    if(isNum) {
                        header.add((String)st.nextElement());
                        isNum = false;
                    }else {
                        st.nextElement();
                        isNum = true;
                    }
                }

                header.add("Class");
            }

            int size = header.size();
            int  columnNums = size;

            {
                String line = br.readLine();

                while (line != null) {
                    size = 0;
                    int[] tempCol = new int[columnNums];

                    StringTokenizer st = new StringTokenizer(line);

                    while (st.hasMoreElements()) {
                        tempCol[size++] = Integer.parseInt((String)st.nextElement());
                    }

                    data.add(tempCol);

                    line = br.readLine();
                }

                rowNums = data.size();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        } finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
