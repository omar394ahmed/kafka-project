package com.demos.firstConsumer.config;

import com.demos.firstConsumer.model.TransactionsWithFees;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@EnableKafka
@Configuration
public class KafkaConfig {
    @Bean
    public ConsumerFactory<Integer, Object> consumerFactory() {

        // Creating a map of string-object type
        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,
                "transaction-consumer");
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, TransactionsWithFees.class);

        // Returning message in JSON format
        return new DefaultKafkaConsumerFactory<>(config);
    }

    // Creating a Listener
    @SuppressWarnings("SpringConfigurationProxyMethods")
    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer,
            TransactionsWithFees>
    transactionListener() {
        ConcurrentKafkaListenerContainerFactory<Integer, TransactionsWithFees> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
