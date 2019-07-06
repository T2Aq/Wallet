package com.t2aq.wallet.data.remote;

public class TestJavaException {

    public static void main(String[] args) {
        TestJavaException testJavaException = new TestJavaException();
        testJavaException.throwException(0);

        try {
            testJavaException.throwCheckedException(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void throwException(int a) {

        if (a == 0) {
            throw new RuntimeException("");
        }
    }

    public void throwCheckedException(int a) throws Exception {
        if (a == 0) {
            throw new RuntimeException("");
        }
    }
}
