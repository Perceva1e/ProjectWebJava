package bsuir.group.projectweb.model;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class ServiceCounter {
    /**
     * This is field for counterRequest.
     */
    private AtomicInteger counterRequest;

}
