package com.demos.frontService.CSVReader.serviceImp;


import com.demos.frontService.CSVReader.FileReader;
import com.demos.frontService.CSVReader.TransactionDetails;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvReader implements FileReader {


    List<Record> records;

    @Override
    public List<Record> readFile(MultipartFile file) throws IOException {
        InputStream stream = file.getInputStream();

        CsvParserSettings csvParserSetting = new CsvParserSettings();

        csvParserSetting.setHeaderExtractionEnabled(true);
        CsvParser csvParser = new CsvParser(csvParserSetting);

        records = csvParser.parseAllRecords(stream);

//        records.stream().forEach(record -> {
//            String firstName = record.getString("firstName");
//            System.out.println("firstName" + firstName);
//            String lastName = record.getString("lastName");
//            System.out.println("lastName" + lastName + "\n");
//        });
        return records;
    }


    @Override
    public List<TransactionDetails> mapRecordsToSpecificObject(List<Record> records) {

        List<TransactionDetails> transactionDetails = records.stream().map(record -> {
            return new TransactionDetails(
                    record.getString("national_id"),
                    record.getString("name"),
                    record.getLong("amount"));
        }).collect(Collectors.toList());


        return transactionDetails;
    }

}
