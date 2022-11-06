package com.demos.firstConsumer.services;

import com.demos.firstConsumer.model.TransactionsWithFees;
import com.demos.firstConsumer.utility.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MainService {



    @Autowired
    FileHelper fileHelper;

    public void saveTransaction(TransactionsWithFees transactionsWithFees) throws IOException {

        // save transactionWithFees into File
        fileHelper.saveToFile(transactionsWithFees);

    }

}
