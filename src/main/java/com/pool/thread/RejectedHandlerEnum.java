package com.pool.thread;

public enum RejectedHandlerEnum {
    DISCARDPOLICY("discardPolicy"),ABORTPOLICY("abortPolicy"),
    DISCARDOLDESTPOLICY("discardOldestPolicy"),CALLERRUNSPOLICY("callerRunsPolicy");

    public String name;

    RejectedHandlerEnum(String name) {
        this.name = name;
    }

}
