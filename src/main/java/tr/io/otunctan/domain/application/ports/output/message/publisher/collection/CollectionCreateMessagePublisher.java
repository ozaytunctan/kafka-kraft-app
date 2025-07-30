package tr.io.otunctan.domain.application.ports.output.message.publisher.collection;

import tr.io.otunctan.domain.application.dto.collection.CollectionAccrualOutboxMessage;
import tr.io.otunctan.infrastructure.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface CollectionCreateMessagePublisher {

    void publish(CollectionAccrualOutboxMessage collectionAccrualOutboxMessage,
                 BiConsumer<CollectionAccrualOutboxMessage, OutboxStatus> callback);
}
