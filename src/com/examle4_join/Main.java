package com.examle4_join;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(0L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);

        List<FactorialThread> threads = new ArrayList<>();

        for (long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.setDaemon(true);
            thread.join(2000); // 숫자 하나가 너무 크면? -> 2초를 넘지않도록! -> 쓰레드가 죽지않아 프로그램이 계속 살아있음
        }

        for ( int i = 0 ; i < inputNumbers.size() ; i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinishied()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }

    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinishied = false;

        public FactorialThread(long inputNumber) { this.inputNumber = inputNumber; }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinishied = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public boolean isFinishied() {
            return isFinishied;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
