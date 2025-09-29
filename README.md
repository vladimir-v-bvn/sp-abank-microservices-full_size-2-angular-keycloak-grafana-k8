Abank: Full-size microservices project on Spring Cloud.

All application and infrastructure services are dockerized and istalled in Kubernetes.

Distributed tracing with Tempo, metrics with Micrometer/Prometheus, logs collection with Loki
and Grafana monitorig tool are provided for all services.

Technologies used:

  MongoDB, MySQL
  
  Spring Cloud, Spring MVC, Spring Data MongoDB, Spring Data JPA, Spring Security
  
  KeyCloak
  
  Circuit Breaker/Resilience4j
  
  OpenAPI/Swagger
  
  Kafka, Avro, Schema Registry

  Loki, Tempo, Grafana
  
  Micrometer, Prometheus
  
  Docker, Docker Compose, Kubernetes

api-gateway provides common logon to the apllication,
  uses KeyCloak to manage users and provide JWT tokens to access services,
  secures the application with Spring Security,
  routes requests to secured endpoints.
  
account-service provides endpoint to create an account in MongoDB.

crdcard-service provides endpoint to create a credit card,
  connects to deposit-service to check availability of funds through different REST clients,
  creates a credit card in MySQL,
  sends messages to the notification-service through Kafka using Avro serializer and Schema Registry.
  
deposit-service reads data from MySQL and provides endpoint to check availability of funds.

notification-service reads messages from Kafka and sends e-mails.

