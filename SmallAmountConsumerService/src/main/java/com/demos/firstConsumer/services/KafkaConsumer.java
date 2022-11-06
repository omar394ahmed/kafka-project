package com.demos.firstConsumer.services;

import com.demos.firstConsumer.model.TransactionsWithFees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaConsumer {


    @Autowired
    MainService mainService;


    @KafkaListener(topics = "smallAmountTopic",
            groupId = "transaction-consumer",
            containerFactory = "transactionListener")
    public void consume(TransactionsWithFees transactionsWithFees) {
        // Print statement
        System.out.println("transaction Record  = " + transactionsWithFees.getAmount());

        try {
            mainService.saveTransaction(transactionsWithFees);
        } catch (IOException e) {

            System.out.println("EXCEPTION ON SAVE PROCESS" + e);
        } catch (Exception e) {
            System.out.println("General Exception" + e);
        }

    }
}
