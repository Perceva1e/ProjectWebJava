package bsuir.group.projectweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ProjectWebApplication {
    /**
     * This is a main of project.
     * @param args params of command console
     */
    public static void main(final String[] args) {
        SpringApplication.run(ProjectWebApplication.class, args);
    }

}
