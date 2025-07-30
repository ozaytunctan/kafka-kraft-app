package tr.io.otunctan.infrastructure.kafka.producer.exception;

public class KafkaProducerException extends RuntimeException{

    public KafkaProducerException() {
    }

    public KafkaProducerException(String message) {
        super(message);
    }

    public KafkaProducerException(String message, Throwable cause) {
        super(message, cause);
    }
}
