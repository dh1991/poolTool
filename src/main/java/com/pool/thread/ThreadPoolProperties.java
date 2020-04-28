package com.pool.thread;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.*;

@ConfigurationProperties(
        prefix = "thread.pooling"
)
public class ThreadPoolProperties {
    private boolean isOpen;
    private Integer maximumPoolSize = 30;
    private Integer corePoolSize = 10;
    private Long keepAliveTime = 1L;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    private Integer queueSize = 1024;
    private QueueEnum queueEnum = QueueEnum.ARRAYBLOCKINGQUEUE;
    private RejectedHandlerEnum handler = RejectedHandlerEnum.ABORTPOLICY;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Integer getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Integer getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Integer queueSize) {
        this.queueSize = queueSize;
    }

    public QueueEnum getQueueEnum() {
        return queueEnum;
    }

    public void setQueueEnum(QueueEnum queueEnum) {
        this.queueEnum = queueEnum;
    }

    public RejectedHandlerEnum getHandler() {
        return handler;
    }

    public void setHandler(RejectedHandlerEnum handler) {
        this.handler = handler;
    }

    protected RejectedExecutionHandler getRejectedHandler(){
        switch (this.handler.name){
            case "discardOldestPolicy":
                return new ThreadPoolExecutor.DiscardOldestPolicy();
            case "discardPolicy":
                return new ThreadPoolExecutor.DiscardPolicy();
            case "callerRunsPolicy":
                return new ThreadPoolExecutor.CallerRunsPolicy();
            default:
                return new ThreadPoolExecutor.AbortPolicy();
        }
    }

    protected BlockingQueue getQueue(){
        switch (this.queueEnum.name){
            case "arrayBlockingQueue":
                return new ArrayBlockingQueue<Runnable>(this.queueSize);
            default:
                return new LinkedBlockingQueue<Runnable>(this.queueSize);
        }
    }
}
