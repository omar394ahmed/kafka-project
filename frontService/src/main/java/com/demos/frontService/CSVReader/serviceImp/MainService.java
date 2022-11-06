package com.demos.frontService.CSVReader.serviceImp;


import com.demos.frontService.CSVReader.FileReader;
import com.demos.frontService.CSVReader.TransactionDetails;
import com.univocity.parsers.common.record.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MainService {


    String topicName = "firstTopic";
    @Autowired
    FileReader csvReader;

    @Autowired
    ProducerService producerService;

    public void ProcessData(MultipartFile file) throws IOException {

        // parse file records
        List<Record> records = csvReader.readFile(file);

        List<TransactionDetails> transactionDetails = csvReader.mapRecordsToSpecificObject(records);

        // init producer
        producerService.initProducer();

        // push records to kafka specific  topic ;
        transactionDetails.forEach(record -> {
            producerService.pushToTopic(topicName,
                    record);
            System.out.println(record.toString());
        });


    }

}
