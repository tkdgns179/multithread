package com.example1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("We are in thread: " + Thread.currentThread().getName());
            System.out.println("Current thread priorty is " + Thread.currentThread().getPriority());

            throw new RuntimeException("Intentional Exception");
        });

        // thread-0 -> New Worker Thread
        thread.setName("New Worker Thread");

        // thread 우선순위 할당
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("A critical error happened in thread " + t.getName() + " the error is " + e.getMessage());
        });
        // JVM thread 생성해 운영체제에 전달 && 새로 생성된 쓰레드임으로 스케줄링 되지않음
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");

        Thread.sleep(10000);
    }
}
