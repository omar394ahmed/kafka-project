package com.demos.ProjectStream.config;

import com.demos.ProjectStream.model.TransactionDetails;
import com.demos.ProjectStream.model.TransactionsWithFees;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class TransactionSerde {

    private TransactionSerde() {
    }

    public static Serde<TransactionDetails> TransactionDetails() {
        JsonSerializer<TransactionDetails> serializer = new JsonSerializer<>();
        JsonDeserializer<TransactionDetails> deserializer = new JsonDeserializer<>(TransactionDetails.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<TransactionsWithFees> withFeesSerde() {
        JsonSerializer<TransactionsWithFees> serializer = new JsonSerializer<>();
        JsonDeserializer<TransactionsWithFees> deserializer = new JsonDeserializer<>(TransactionsWithFees.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
