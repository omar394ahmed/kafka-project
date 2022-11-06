package com.demos.ProjectStream.service;


import com.demos.ProjectStream.model.TransactionDetails;
import com.demos.ProjectStream.model.TransactionsWithFees;
import com.demos.ProjectStream.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MainService {


    @Autowired
    FeesCalculatorService feesCalculatorService;
    @Autowired
    ExchangeService exchangeService;

    @Autowired
    Mapper mapper;

    @Value("${Doller.exchangeRate}")
    int exchanegRate;

    @Value("${bigAmount.fees}")
    int bigAmountsFees;

    @Value("${smallAmount.fees}")
    int smallAmountsFees;


    public TransactionsWithFees processBigAmount(TransactionDetails transactionDetails) {
        // first make exchange from EGP TO $
        Long amountInDoller = exchangeService.exchangeAmount(transactionDetails.getAmount(), exchanegRate);
        // set new value with doller
        transactionDetails.setAmount(amountInDoller);
        // calculate fees
        Long fees = feesCalculatorService.calculateFees(transactionDetails.getAmount(), bigAmountsFees);
        // map to new Object TransactionWithFees
        TransactionsWithFees transactionsWithFees = mapper.mapTransactionToTransactionWithFees(transactionDetails, fees);

        return transactionsWithFees;
    }

    public TransactionsWithFees processSmallAmount(TransactionDetails transactionDetails) {
        // calculate fees
        Long fees = feesCalculatorService.calculateFees(transactionDetails.getAmount(), smallAmountsFees);
        // map to new Object TransactionWithFees
        TransactionsWithFees transactionsWithFees = mapper.mapTransactionToTransactionWithFees(transactionDetails, fees);

        return transactionsWithFees;
    }


}
