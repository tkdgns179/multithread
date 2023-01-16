package com.example11_data_rece;

public class Main {

    public static void main(String[] args) {

        SharedClass sharedClass = new SharedClass();

        Thread threadA = new Thread(() -> {
           for (int i = 0; i < Integer.MAX_VALUE ; i++) {
               sharedClass.increment();
           }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE ; i++) {
                sharedClass.checkForDataRace();
            }
        });
        threadA.setName("Increment");
        threadB.setName("CheckForDataRace");
        threadA.start();
        threadB.start();

    }

    public static class SharedClass {
        private volatile int x = 0;
        private volatile int y = 0;

        // synchronized 키워드 넣어봄
        public void increment() {
            x++;
            y++;
        }

        public void checkForDataRace() {
            if (y > x) {
                System.out.println("y > x - Data Race is detected");
            }
        }
    }

}
