package tr.io.otunctan.domain.application.dto.collection;

import java.io.Serializable;

public class CollectionAccrualOutboxMessage implements Serializable {

    private String sagaId;

    public String getSagaId() {
        return sagaId;
    }

    public void setSagaId(String sagaId) {
        this.sagaId = sagaId;
    }
}
