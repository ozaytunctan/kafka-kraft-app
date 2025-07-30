package tr.io.otunctan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tr.io.otunctan.domain.application.dto.collection.CollectionAccrualOutboxMessage;
import tr.io.otunctan.domain.application.ports.output.message.publisher.collection.CollectionCreateMessagePublisher;
import tr.io.otunctan.infrastructure.outbox.OutboxStatus;

import java.util.UUID;

@SpringBootApplication
public class KafkaDemoAppApplication {


    private final CollectionCreateMessagePublisher collectionCreateMessagePublisher;

    public KafkaDemoAppApplication(CollectionCreateMessagePublisher collectionCreateMessagePublisher) {
        this.collectionCreateMessagePublisher = collectionCreateMessagePublisher;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoAppApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(){
        return (args)->{
            CollectionAccrualOutboxMessage collectionAccrualOutboxMessage = new CollectionAccrualOutboxMessage();
            collectionAccrualOutboxMessage.setSagaId(UUID.randomUUID().toString());
            collectionCreateMessagePublisher.publish(collectionAccrualOutboxMessage,
                    this::updateOutboxMessage);
        };
    }

    private void updateOutboxMessage(CollectionAccrualOutboxMessage collectionAccrualOutboxMessage, OutboxStatus outboxStatus) {

    }

}
