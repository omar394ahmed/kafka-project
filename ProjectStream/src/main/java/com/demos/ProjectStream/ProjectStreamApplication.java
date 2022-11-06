package com.demos.ProjectStream;

import com.demos.ProjectStream.config.TransactionsStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProjectStreamApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProjectStreamApplication.class, args);

        TransactionsStream transactionsStream = context.getBean(TransactionsStream.class);
        transactionsStream.init();
    }


}
