package com.demos.ProjectStream.service;


import org.springframework.stereotype.Service;

@Service
public class ExchangeService {


    public Long exchangeAmount(Long Amount, int ExchangeRate) {

        Long AmountInDollar = Amount / ExchangeRate;
        return AmountInDollar;
    }
}
