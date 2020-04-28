package com.pool.thread;

public enum QueueEnum {
    ARRAYBLOCKINGQUEUE("arrayBlockingQueue"),LINKEDBLOCKINGQUEUE("linkedBlockingQueue");

    public String name;

    QueueEnum(String name) {
        this.name = name;
    }
}
