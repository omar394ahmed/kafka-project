package com.demos.frontService.CSVReader;


import com.demos.frontService.CSVReader.serviceImp.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class CSVController {


    @Autowired
    MainService mainService;

    @PostMapping
    public String read(@RequestParam("file") MultipartFile file) throws IOException {
        mainService.ProcessData(file);
        return "Success";
    }
}
