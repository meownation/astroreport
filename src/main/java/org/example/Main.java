package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

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

    public static String addDay(String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(date));
        calendar.add(Calendar.DATE, 1);  // number of days to add to your existing date
        return sdf.format(calendar.getTime());

    }


 //   @Scheduled(fixedRate = 3000)

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            LocalDate date= LocalDate.now();
            String sqldate =Date.valueOf(date).toString();
            for(int i=0;i<10;i++) {
                File f = new File(String.format("target/%s.txt", sqldate));


                f.delete();
                DailyHoroscope dailyHoroscope = null;
                for (AstroSign sign : AstroSign.values()) {

                    dailyHoroscope =
                            restTemplate.getForObject(
                                    "http://localhost:8080/daily/{sign}?date={sqldate}", DailyHoroscope.class, sign, sqldate);

                    FileUtils.writeStringToFile(f, dailyHoroscope.toString(), "UTF-8", true);
                    log.info(dailyHoroscope.toString());
                    System.out.println(dailyHoroscope);
                }
                sqldate= addDay(sqldate);

            }
        };
    }




}