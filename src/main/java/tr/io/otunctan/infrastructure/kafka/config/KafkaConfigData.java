package tr.io.otunctan.infrastructure.kafka.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "kafka.config")
public class KafkaConfigData {

    private String bootstrapServers; //borker serverlar

//    private String schemaRegistryUrlKey;
//    private String schemaRegistryUrl;
    private Integer numOfPartitions;// default partition sayısı
    private Short replicationFactor; // default replica sayısı

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public Integer getNumOfPartitions() {
        return numOfPartitions;
    }

    public void setNumOfPartitions(Integer numOfPartitions) {
        this.numOfPartitions = numOfPartitions;
    }

    public Short getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(Short replicationFactor) {
        this.replicationFactor = replicationFactor;
    }
}
