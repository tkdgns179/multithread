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
            // 입력된 값 3, 4 (최댓값 10)일 때 ThreadA가 최솟값을 3으로 바꿨는데 ThreadB가 최솟값을 4으로
            // 바꾼다면 원자성이 깨지기 때문에 임계영역을 정의함
            // public void synchronized addSample(long newSample); 로 정의해도 됨
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
