package com.suichen.plsa;

/**
 * Created by suichen on 2015/8/25.
 */
public class ErrorReport {
    public static void showMessage(Object errobj, String methodName) {
        System.err.println("Error in class: "+errobj.getClass().getName()+
        "\tMethod: "+methodName);
    }

    public static void showMessage(Object errobj, String methodName, String addition) {
        System.err.println("Error in class: " + errobj.getClass().getName()
                + "\tMethod: " + methodName + "\tAddition: " + addition);
    }
}
