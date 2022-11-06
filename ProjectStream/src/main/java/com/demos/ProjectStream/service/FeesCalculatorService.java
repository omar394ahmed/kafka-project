package com.demos.ProjectStream.service;


import org.springframework.stereotype.Service;

@Service
public class FeesCalculatorService {

    public Long calculateFees(Long amount, int feesPercentage) {

        Long fees = amount * feesPercentage / 100;
        return fees;
    }


}
