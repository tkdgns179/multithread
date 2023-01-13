package com.example2;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    // final
    private final List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {

        List<Thread> threads = new ArrayList<>();

        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
