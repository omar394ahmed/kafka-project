package com.demos.frontService.CSVReader.serviceImp;


import com.demos.frontService.CSVReader.TransactionDetails;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class ProducerService {


    KafkaProducer<Integer, TransactionDetails> producer;

    public boolean initProducer() {

        Properties properties = new Properties();
        KafkaProperties kafkaProperties = new KafkaProperties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        properties.setProperty(JsonSerializer.ADD_TYPE_INFO_HEADERS, "false");
        producer = new KafkaProducer<Integer, TransactionDetails>(properties);


        return true;
    }


    public void pushToTopic(String topic, TransactionDetails transaction) {

        ProducerRecord<Integer, TransactionDetails> producerRecord =
                new ProducerRecord<Integer, TransactionDetails>(topic, transaction);

        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e != null) {
                    System.out.println("exception " + e);
                }
            }
        });

        producer.flush();

    }
}
