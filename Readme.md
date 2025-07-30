## Postgresql veritabanı container olarak çalıştırmak için
docker-compose -f common.yml --env-file .\.env -f .\postgres_cluster.yml up -d
## Kafka cluster çalıştırmak  için 
docker-compose -f common.yml --env-file .\.env -f .\kafka_cluster.yml up -d

