package bsuir.group.projectweb.model;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class ServiceCounter {

    private AtomicInteger counterRequest;

}
