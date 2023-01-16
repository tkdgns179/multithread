package com.example10_atomic;

public class QuizMinMaxMetrics {


    public static class MinMaxMetrics {

        // Add all necessary member variables
        private volatile long MAX;
        private volatile long MIN;

        /**
         * Initializes all member variables
         */
        public MinMaxMetrics() {
            // Add code here
            this.MIN = Long.MAX_VALUE;
            this.MAX = Long.MIN_VALUE;
        }

        /**
         * Adds a new sample to our metrics.
         */
        public void addSample(long newSample) {
//            // Add code here
//            if (newSample > MAX) {
//                MAX = newSample;
//            }
//            if (newSample < MIN) {
//                MIN = newSample;
//            }

            // 정답
            synchronized (this) {
                this.MIN = Math.min(newSample, this.MIN);
                this.MAX = Math.min(newSample, this.MAX);
            }


        }

        /**
         * Returns the smallest sample we've seen so far.
         */
        public long getMin() {
            // Add code here
            return this.MIN;
        }

        /**
         * Returns the biggest sample we've seen so far.
         */
        public long getMax() {
            // Add code here
            return this.MAX;
        }
    }


}
