package com.demos.ProjectStream.utility;

import com.demos.ProjectStream.model.TransactionDetails;
import com.demos.ProjectStream.model.TransactionsWithFees;
import org.springframework.stereotype.Service;

@Service
public class Mapper {


    public TransactionsWithFees mapTransactionToTransactionWithFees(TransactionDetails transaction, Long fees) {

        return new TransactionsWithFees(transaction.getNational_id(), transaction.getName(), transaction.getAmount(), fees);

    }
}
