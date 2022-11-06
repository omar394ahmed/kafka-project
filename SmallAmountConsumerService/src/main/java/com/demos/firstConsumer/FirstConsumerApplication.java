package com.demos.firstConsumer;

import com.demos.firstConsumer.utility.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class FirstConsumerApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(FirstConsumerApplication.class, args);
//
    }

    @Autowired
    FileHelper fileHelper;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws IOException {
        System.out.println("APP starts........");
        fileHelper.createFile();
    }

}
