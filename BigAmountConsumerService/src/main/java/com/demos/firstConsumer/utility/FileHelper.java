package com.demos.firstConsumer.utility;

import com.demos.firstConsumer.model.TransactionsWithFees;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


@Service
@PropertySource("classpath:application.properties")
public class FileHelper {

    @Value("${file.name}")
    String fileName;

    public void createFile() throws IOException {

        FileWriter fileWriter = new FileWriter(fileName, false);
        BufferedWriter info = new BufferedWriter(fileWriter);

        Arrays.stream(TransactionsWithFees.class.getDeclaredFields())
                .forEach(field -> {
                    try {

                        info.write(field.getName().equals("amount") ? field.getName() + "$," : field.getName()+",");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        info.newLine();
        info.close();

    }

    public void saveToFile(TransactionsWithFees transactionsWithFees) throws IOException {

        FileWriter fstream = new FileWriter(fileName, true);
        BufferedWriter info = new BufferedWriter(fstream);

        String name = transactionsWithFees.getName();
        String national_id = transactionsWithFees.getNational_id();
        Long amount = transactionsWithFees.getAmount();
        Long fees = transactionsWithFees.getFees();

        info.write(name + ",");
        info.write(national_id + ",");
        info.write(amount + ",");
        info.write(fees.toString() + "\n");

        info.close();
    }
}
