package hr.fer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class PlatformApplication extends SpringBootServletInitializer {

    /**
     * Used when run as JAR
     */
    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }
}