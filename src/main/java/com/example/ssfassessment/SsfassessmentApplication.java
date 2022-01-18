package com.example.ssfassessment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class SsfassessmentApplication {

    private static Logger LOGGER = LoggerFactory.getLogger("ssfassessmentApplication.class");

    public static void main(String[] args) {
        SpringApplication.run(SsfassessmentApplication.class, args);

        /*SpringApplication app = new SpringApplication(SsfassessmentApplication.class);
        ApplicationArguments arguments = new DefaultApplicationArguments(args);
        String port = "8082";

        if(arguments.containsOption("port")) {
            port = arguments.getOptionValues("port").get(0);
        }
        LOGGER.info("cli argument: {}" , port);

        if(System.getenv("PORT") != null) {
            port = System.getenv("PORT");
        }
        LOGGER.info("env var: " + port);

        app.setDefaultProperties(Collections.singletonMap("server.port", port));
        app.run(args);*/
    }

}
