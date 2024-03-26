package bsuir.group.projectweb.service.impl;

import bsuir.group.projectweb.model.ServiceCounter;

import lombok.Data;


import java.util.concurrent.atomic.AtomicInteger;


@Data
public class CounterServiceImpl {

    private CounterServiceImpl() {}
    private static ServiceCounter serviceCounter = new ServiceCounter();
    private static AtomicInteger newEnhanceCounter = new AtomicInteger(0);

    public static synchronized void enhanceCounter() {
        if(serviceCounter.getCounterRequest() != null) {
            newEnhanceCounter = serviceCounter.getCounterRequest();
        }
        newEnhanceCounter.incrementAndGet();
        serviceCounter.setCounterRequest(newEnhanceCounter);
    }
    public static synchronized int getCounter(){
        AtomicInteger newCounter = serviceCounter.getCounterRequest();
        return newCounter.get();
    }
}
