package org.interview.cleartrip;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {

    AtomicInteger id = new AtomicInteger(0);

    public int getId(){
        return id.incrementAndGet();
    }
}
