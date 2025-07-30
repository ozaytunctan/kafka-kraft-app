package tr.io.otunctan.messaging.collection;

import org.springframework.stereotype.Component;
import tr.io.otunctan.domain.application.dto.collection.CollectionAccrualOutboxMessage;
import tr.io.otunctan.domain.application.ports.output.message.publisher.collection.CollectionCreateMessagePublisher;
import tr.io.otunctan.infrastructure.kafka.producer.KafkaMessageHelper;
import tr.io.otunctan.infrastructure.kafka.producer.service.KafkaProducer;
import tr.io.otunctan.infrastructure.outbox.OutboxStatus;

import java.util.function.BiConsumer;

@Component
public class CollectionCreateKafkaMessagePublisher implements CollectionCreateMessagePublisher {

    private final KafkaProducer<String, CollectionAccrualOutboxMessage> messageKafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;

    public CollectionCreateKafkaMessagePublisher(KafkaProducer<String, CollectionAccrualOutboxMessage> messageKafkaProducer,
                                                 KafkaMessageHelper kafkaMessageHelper) {
        this.messageKafkaProducer = messageKafkaProducer;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(CollectionAccrualOutboxMessage collectionAccrualOutboxMessage,
                        BiConsumer<CollectionAccrualOutboxMessage, OutboxStatus> callback) {

        messageKafkaProducer.send(
                "collection-request",
                "COLLECTION_MESSAGE",
                collectionAccrualOutboxMessage,
                kafkaMessageHelper.getCallback(
                        "collection-request",
                        null,
                        collectionAccrualOutboxMessage,
                        callback
                )
        );

    }
}
