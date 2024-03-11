package bsuir.group.projectweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjectWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectWebApplication.class, args);
    }

}
