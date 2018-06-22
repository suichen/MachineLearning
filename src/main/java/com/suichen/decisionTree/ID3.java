package com.suichen.decisionTree;

/**
 * Created by suichen on 2015/7/27.
 */
public class ID3 {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("The number of arguments is wrong!!!");
            return;
        }

        String trainFileName = args[0];
        String testFileName = args[1];

        ID3Learn id3Learn = new ID3Learn(trainFileName);

    }
}
