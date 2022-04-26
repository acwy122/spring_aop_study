package com.mashibing.service;

import com.mashibing.util.LogUtil;

public class MyCalculator implements Calculator {

    public int add(int i, int j) {
        LogUtil.start(i,j);
        int result = i+j;
        LogUtil.stop(i,j);
        return result;
    }

    public int sub(int i, int j) {
        LogUtil.start(i,j);
        int result = i+j;
        LogUtil.stop(i,j);
        return result;
    }

    public int mul(int i, int j) {
        LogUtil.start(i,j);
        int result = i+j;
        LogUtil.stop(i,j);
        return result;
    }

    public int div(int i, int j) {
        LogUtil.start(i,j);
        int result = i+j;
        LogUtil.stop(i,j);
        return result;
    }
}
