package com.demos.ProjectStream.config;

import com.demos.ProjectStream.model.TransactionDetails;
import com.demos.ProjectStream.model.TransactionsWithFees;
import com.demos.ProjectStream.service.MainService;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;


@Service
public class TransactionsStream {


    KStream<Integer, TransactionDetails> firstTopic;


    KafkaStreams kafkaStreams;


    @Autowired
    MainService mainService;

    @Value("${bootstrap.server}")
    String server;
    @Value("${main.topic.name}")
    private String mainTopic;
    @Value("${bigAmount.topic.name}")
    private String bigAmountTopic;
    @Value("${smallAmount.topic.name}")
    private String smallAmountTopic;

    @Autowired
    public void init() {
        Properties properties = new Properties();

        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "transactions-kafka-streams");
        properties.setProperty(JsonDeserializer.TRUSTED_PACKAGES, "*");
        properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName());
        // properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        properties.setProperty(JsonSerializer.ADD_TYPE_INFO_HEADERS, "false");
        properties.put("default.deserialization.exception.handler", LogAndContinueExceptionHandler.class);
        properties.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, TransactionSerde.TransactionDetails().getClass().getName());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        firstTopic = streamsBuilder.stream(mainTopic, Consumed.with(Serdes.Integer(), TransactionSerde.TransactionDetails()));

        // filter out bigAmounts  transactions
        KStream<Integer, TransactionsWithFees> bigAmountTransactions = firstTopic.filter((integer, transactionDetails) ->
                transactionDetails.getAmount().compareTo(1000L) > 0
        ).mapValues((integer, transactionDetails) -> {

            TransactionsWithFees transactionsWithFees = mainService.processBigAmount(transactionDetails);
            return transactionsWithFees;
        });


        // push them to bigAmountTopic
        bigAmountTransactions.to(bigAmountTopic, Produced.with(Serdes.Integer(), TransactionSerde.withFeesSerde()));

        // filter out smallAmount transactions
        KStream<Integer, TransactionsWithFees> smallAmountTransactions = firstTopic.filter((integer, transactionDetails) ->
                transactionDetails.getAmount().compareTo(1000L) <= 0
        ).mapValues((integer, transactionDetails) -> {
            TransactionsWithFees transactionsWithFees = mainService.processSmallAmount(transactionDetails);
            return transactionsWithFees;
        });
        // push them to SmallAmountTopic
        smallAmountTransactions.to(smallAmountTopic, Produced.with(Serdes.Integer(), TransactionSerde.withFeesSerde()));

        bigAmountTransactions.foreach((integer, transactionDetails) -> System.out.println("Big : -" + transactionDetails.getName() + "  " + transactionDetails.getAmount()));
        smallAmountTransactions.foreach((integer, transactionDetails) -> System.out.println("small : - " + transactionDetails.getName() + "  " + transactionDetails.getAmount()));


        kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);

        kafkaStreams.start();

    }


}
