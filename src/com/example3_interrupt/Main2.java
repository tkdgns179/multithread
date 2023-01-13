package com.example3_interrupt;

import java.beans.BeanInfo;
import java.beans.beancontext.BeanContext;
import java.math.BigInteger;

public class Main2 {

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("20000"), new BigInteger("10000000")));

        // thread.setDaemon(true); // 외부라이브러리 처리 main thread 종료시 전체 앱 종료qqqq
        thread.start();
        
        thread.interrupt(); // not enough 연산이 너무 길어 인터럽트가 걸리지 않음
    }

    private static class LongComputationTask implements Runnable {

        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO ; i.compareTo(power) != 0 ; i.add(BigInteger.ONE)) {
                // 인터럽트 제어
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupt computation");
                    return BigInteger.ONE;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
