package bsuir.group.projectweb.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CounterServiceImplTest {

    @Test
    void enhanceCounter() {
        CounterServiceImpl.enhanceCounter();
        Assertions.assertEquals(1, CounterServiceImpl.getCounter());
    }

    @Test
    void getCounter() {
        CounterServiceImpl.enhanceCounter();
        CounterServiceImpl.enhanceCounter();
        int counter = CounterServiceImpl.getCounter();
        Assertions.assertEquals(3, counter);
    }
}