package com.demos.frontService.CSVReader;


import com.univocity.parsers.common.record.Record;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileReader {


    List<Record> readFile(MultipartFile file) throws IOException;

    List<TransactionDetails> mapRecordsToSpecificObject(List<Record> records);
}
