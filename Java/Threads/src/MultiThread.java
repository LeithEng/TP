class MyThread extends Thread {
    private String threadName;
    private int remainingTime;
    private boolean isPaused;
    private boolean isRunning;

    public MyThread(String threadName, int remainingTime) {
        this.threadName = threadName;
        this.remainingTime = remainingTime;
        this.isPaused = true;
        this.isRunning = true;
    }

    public String getThreadName() {
        return threadName;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public synchronized void pauseThread() {
        isPaused = true;
    }

    public synchronized void resumeThread() {
        isPaused = false;
        notify();
    }

    public synchronized void killThread() {
        isRunning = false;
        remainingTime=0;
        interrupt();
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                synchronized (this) {
                    while (isPaused) {
                        wait();
                    }
                }

                // Decrease remaining time by 1 second
                remainingTime--;

                // Check if remaining time is 0 and stop the thread if so
                if (remainingTime == 0) {
                    killThread();
                    break;
                }

                // Sleep for 1 second
                sleep(1000);

            } catch (InterruptedException e) {
                // Thread interrupted, terminate gracefully
                break;
            }
        }
    }
}