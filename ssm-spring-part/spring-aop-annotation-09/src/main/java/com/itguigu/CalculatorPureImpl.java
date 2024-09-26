package com.itguigu;

public class CalculatorPureImpl implements Calculator {
    @Override
    public int add(int i, int j) {
        System.out.println("i = " + i + ", j = " + j);
        int result = i + j;
        System.out.println("result = " + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("i = " + i + ", j = " + j);
        int result = i - j;
        System.out.println("result = " + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("i = " + i + ", j = " + j);
        int result = i * j;
        System.out.println("result = " + result);
        return result;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("i = " + i + ", j = " + j);
        int result = i / j;
        System.out.println("result = " + result);
        return result;
    }
}
