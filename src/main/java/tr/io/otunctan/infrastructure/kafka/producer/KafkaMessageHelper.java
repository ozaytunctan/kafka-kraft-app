package tr.io.otunctan.infrastructure.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import tr.io.otunctan.infrastructure.outbox.OutboxStatus;

import java.util.function.BiConsumer;

@Component
public class KafkaMessageHelper {

    private Logger log = LoggerFactory.getLogger(KafkaMessageHelper.class);

    public <T, U> BiConsumer<SendResult<String, T>, Throwable> getCallback(String topicName,//
                                                                           T message,//
                                                                           U outboxMessage,//
                                                                           BiConsumer<U, OutboxStatus> outboxCallback) {
        return (stringTSendResult, throwable) -> {
            if (throwable != null) {
                log.error("Error while sending message {} to topic {}", message, topicName, throwable);
                outboxCallback.accept(outboxMessage, OutboxStatus.FAILED);
            } else if (stringTSendResult != null) {
                var metadata = stringTSendResult.getRecordMetadata();
                log.info("Received new metadata. Topic: {}; Partition: {}; Offset: {}; Timestamp: {}; at time: {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
                outboxCallback.accept(outboxMessage, OutboxStatus.COMPLETED);
            }
        };
    }
}
