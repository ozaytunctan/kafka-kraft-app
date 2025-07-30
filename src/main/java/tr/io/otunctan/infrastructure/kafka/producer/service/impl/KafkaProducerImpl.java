package tr.io.otunctan.infrastructure.kafka.producer.service.impl;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import tr.io.otunctan.infrastructure.kafka.producer.exception.KafkaProducerException;
import tr.io.otunctan.infrastructure.kafka.producer.service.KafkaProducer;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;


@Component
public class KafkaProducerImpl<K extends Serializable, V> implements KafkaProducer<K, V> {

    private Logger logger = LoggerFactory.getLogger(KafkaProducerImpl.class);

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message,
                     BiConsumer<SendResult<K, V>,Throwable> callback) {

        logger.info("Sending message={} to topic={}", message, topicName);
        try {
            CompletableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(topicName, key, message);
            kafkaResultFuture.whenCompleteAsync(callback);// thread pool da verilebilir.
        } catch (KafkaException e) {
            logger.error("Error on kafka producer with key: {}, message: {} and exception: {}", key, message,
                    e.getMessage());
            throw new KafkaProducerException("Error on kafka producer with key: " + key + " and message: " + message);
        }

    }


    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            logger.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }
}
