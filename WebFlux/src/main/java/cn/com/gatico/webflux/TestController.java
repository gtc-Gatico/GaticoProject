package cn.com.gatico.webflux;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Random;


@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "test1", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> test() {
        System.out.println("test1:" + Thread.currentThread().getName());
        Flux<String> stringFlux = Flux.fromStream(new Random().ints(10).mapToObj(intStream ->
        {
            try {
                char  a = '中';

                System.out.println("test1:" + Thread.currentThread().getName());
//                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "this is data" + intStream;
        }));
        System.out.println("test1:" + Thread.currentThread().getName());
        return stringFlux;
    }
}
