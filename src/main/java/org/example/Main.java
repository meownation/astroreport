package org.example;

import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@SpringBootApplication
public class Main {


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


 //   @Scheduled(fixedRate = 3000)

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            File f = new File("target/test.txt");
            f.delete();
            DailyHoroscope dailyHoroscope = null;
            for (AstroSign sign :AstroSign.values()){

                dailyHoroscope =
                    restTemplate.getForObject(
                    "http://localhost:8080/daily/{sign}", DailyHoroscope.class, sign);

                FileUtils.writeStringToFile(f, dailyHoroscope.toString(),"UTF-8", true);
            log.info(dailyHoroscope.toString());
            System.out.println(dailyHoroscope);}



        };
    }




}