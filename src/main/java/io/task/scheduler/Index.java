package io.task.scheduler;

import java.util.concurrent.atomic.AtomicLong;


public final class Index {

    volatile static AtomicLong NEXT_ID = new AtomicLong(0);
    private static long currentId;
    private static long nextId;

    public static long getCurrentId() {
        return NEXT_ID.get();
    }

    public static long getNextId() {
        return NEXT_ID.getAndIncrement();
    }

}
