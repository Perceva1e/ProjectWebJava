package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.model.ServiceCounter;

import lombok.Data;


import java.util.concurrent.atomic.AtomicInteger;


@Data
public final class CounterServiceImpl {

    private CounterServiceImpl() {
    }

    /**
     * This service of Counter.
     */
    private static ServiceCounter serviceCounter = new ServiceCounter();
    /**
     * This is variable for operation on counter.
     */
    private static AtomicInteger newEnhanceCounter = new AtomicInteger(0);

    /**
     * This method of enchance counter.
     */
    public static synchronized void enhanceCounter() {
        if (serviceCounter.getCounterRequest() != null) {
            newEnhanceCounter = serviceCounter.getCounterRequest();
        }
        newEnhanceCounter.incrementAndGet();
        serviceCounter.setCounterRequest(newEnhanceCounter);
    }

    /**
     * This method get number of counter .
     *
     * @return number of counter
     */
    public static synchronized int getCounter() {
        AtomicInteger newCounter = serviceCounter.getCounterRequest();
        return newCounter.get();
    }
}
