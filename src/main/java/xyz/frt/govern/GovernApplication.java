package xyz.frt.govern;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.frt.govern.dao")
public class GovernApplication {

    public static void main(String[] args) {
        SpringApplication.run(GovernApplication.class, args);
    }
}
