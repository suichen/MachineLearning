package com.suichen.decisionTree;

import java.util.ArrayList;

/**
 * Created by suichen on 2015/7/27.
 */
public class TreeNode {
    private String attrName;
    private int attrVaue;
    private double informationGain;
    private boolean flag; //判断是否该节点是叶节点
    private int fClass; //判断该叶节点的类型，是第一类还是第二类
    private ArrayList<TreeNode> child;

    public TreeNode(String attrName, int attrVaue, double informationGain, ArrayList<TreeNode> child, boolean flag) {
        this.attrName = attrName;
        this.attrVaue = attrVaue;
        this.informationGain = informationGain;
        this.child = child;
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getfClass() {
        return fClass;
    }

    public void setfClass(int fClass) {
        this.fClass = fClass;
    }

    public TreeNode() {
        child = new ArrayList<TreeNode>();
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public int getAttrVaue() {
        return attrVaue;
    }

    public void setAttrVaue(int attrVaue) {
        this.attrVaue = attrVaue;
    }

    public double getInformationGain() {
        return informationGain;
    }

    public void setInformationGain(double informationGain) {
        this.informationGain = informationGain;
    }

    public ArrayList<TreeNode> getChild() {
        return child;
    }

    public void setChild(ArrayList<TreeNode> child) {
        this.child = child;
    }
}
