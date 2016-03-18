package com.zhuinden.firsttestingsetup;

/**
 * Created by Zhuinden on 2016.03.18..
 */
public class Something {
    public String someMethod() {
        return "Real";
    }

    public String otherMethod() {
        return someMethod();
    }

    public interface SomeCallback {
        void onFinished(Result result);
    }

    public static class Result {
        public Result(long currentTime) {
            this.currentTime = currentTime;
        }

        private long currentTime;

        public long getCurrentTime() {
            return currentTime;
        }
    }

    public Void someAsyncMethod(final SomeCallback someCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                otherMethod();
                Result result = new Result(System.currentTimeMillis());
                someCallback.onFinished(result);
            }
        }).start();
        return null;
    }
}
