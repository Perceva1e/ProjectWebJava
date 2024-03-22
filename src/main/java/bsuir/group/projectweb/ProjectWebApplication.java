package bsuir.group.projectweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ProjectWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectWebApplication.class, args);
    }

}
