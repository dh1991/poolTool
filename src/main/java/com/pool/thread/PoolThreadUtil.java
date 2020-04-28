package com.pool.thread;

import java.util.concurrent.ThreadPoolExecutor;

public class PoolThreadUtil {

    private ThreadPoolExecutor threadPoolExecutor;

    protected PoolThreadUtil(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public void execute(Runnable runnable){
        threadPoolExecutor.execute(runnable);
    }
}
