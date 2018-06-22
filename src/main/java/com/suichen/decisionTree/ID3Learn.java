package com.suichen.decisionTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suichen on 2015/7/27.
 */
public class ID3Learn {
    private String fileName;

    public ID3Learn(String fileName) {
        this.fileName = fileName;
    }

    public void addUnique(List<Integer> list, int target) {
        if(!list.contains(target))
            list.add(target);
    }
    public boolean checkLabel(int[] label, int target) {
        for (int i = 0; i < label.length; i++) {
            if(label[i] != target)
                return false;
        }
        return true;
    }

    public int getPositiveCount(int[] label) {
        int count = 0;

        for(int i = 0; i < label.length; i++) {
            if(label[i] == 1)
                count++;
        }

        return count;
    }

    public static double log2(double num) {
        if(num <= 0)
            return 0.0;

        return (Math.log(num)/Math.log(2));
    }
    public double getEntropy(int[] label) {
        int positives = 0, negatives = 0;

        for(int i = 0; i < label.length; i++) {
            if(label[i] == 0)
                negatives++;
            else if(label[i] == 1)
                positives++;
        }

        double a = (double)positives/(positives+negatives);
        double b = (double)negatives/(positives+negatives);

        return -(a/log2(a)+b/log2(b));
    }

    public void learnTree(MatrixData matrixData, int[] label, Map<String, int[]> trainVector, TreeNode node) {

        if(checkLabel(label, 0)) {
            node.setFlag(true);
            node.setfClass(0);
            return;
        } else if(checkLabel(label,1)){
            node.setFlag(true);
            node.setfClass(1);
            return;
        }

        if(trainVector.keySet().size() <= 1) {//如果只有一个属性了
            int count = getPositiveCount(label);
            if(label.length >= count) {
                node.setfClass(0);
            } else {
                node.setfClass(1);
            }
        }else {//用信息增益选择属性

            double Entropy = getEntropy(label);

            for(Map.Entry entry:trainVector.entrySet()) {
                HashMap<Integer, Integer> atrPositive = new HashMap<Integer, Integer>();
                HashMap<Integer, Integer> atrNegative = new HashMap<Integer, Integer>();
                List<Integer> atrUnique = new ArrayList<Integer>();

                int[] training = (int[])entry.getValue();

                for(int i = 0; i < training.length; i++) {

                    addUnique(atrUnique, training[i]);

                    if(label[i] == 0) {//负
                        if(atrNegative.containsKey(training[i])) {
                            atrNegative.put(training[i], atrNegative.get(training[i])+1);
                        }else {
                            atrNegative.put(training[i], 1);
                        }
                    }else if(label[i] == 1) {//正
                        if(atrPositive.containsKey(training[i])) {
                            atrPositive.put(training[i], atrPositive.get(training[i])+1);
                        }else {
                            atrPositive.put(training[i],1);
                        }
                    }
                }

                { //now calculate gain
                    for (int tempArr : atrUnique) {

                    }
                }
            }
        }

    }
   public void startLearning() {
       MatrixData matrixData = new MatrixData();
       matrixData.preadyData(fileName);

       System.out.println("==============将数据放到矩阵中==============");

       Map<String, int[]> attributeVector = new HashMap<String, int[]>();

        for (int i = 0; i < matrixData.getColumnNums()-1; i++) {
            int[] columnVector = new int[matrixData.getRowNums()];

            matrixData.fillMatrix(columnVector, i);

            attributeVector.put(matrixData.getHeader().get(i), columnVector);
        }

       int[] label = new int[matrixData.getRowNums()];
       matrixData.fillMatrix(label, matrixData.getColumnNums()-1);

       TreeNode root = new TreeNode();

   }


}
