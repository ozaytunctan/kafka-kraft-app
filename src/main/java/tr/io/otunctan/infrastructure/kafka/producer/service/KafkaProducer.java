package tr.io.otunctan.infrastructure.kafka.producer.service;

import org.springframework.kafka.support.SendResult;

import java.io.Serializable;
import java.util.function.BiConsumer;

public interface KafkaProducer<K extends Serializable, V> {
    void send(String topicName,
              K key,
              V message,
              BiConsumer<SendResult<K, V>,Throwable> callback);
}
