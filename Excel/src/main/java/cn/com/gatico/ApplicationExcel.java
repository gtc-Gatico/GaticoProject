package cn.com.gatico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApplicationExcel {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationExcel.class, args);
    }

}
