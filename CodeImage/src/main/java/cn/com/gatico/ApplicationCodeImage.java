package cn.com.gatico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApplicationCodeImage {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationCodeImage.class, args);
    }

}
