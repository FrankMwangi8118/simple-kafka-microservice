# Kafka Messaging Microservice (Spring Boot)
This is a simple Spring Boot microservice that demonstrates basic **Apache Kafka** integration. It includes: - A **Kafka Producer** that publishes simple string messages. - A **Kafka Consumer** that listens for 
messages and logs them. ---
## 🛠️ Tech Stack
- Java 17+ 
- Spring Boot 3.x 
- Apache Kafka 
- Spring Kafka 
- Maven ---
# Kafka Message Publisher and Consumer with Spring Boot
This is a simple microservices-based demo project that demonstrates how to use **Apache Kafka** with **Spring Boot** to publish and consume string messages.
## 🏗 Architecture
- **pingmyserver** → Kafka **Producer** Publishes string messages to a Kafka topic. 
- **notification-service** → Kafka **Consumer** Listens to the topic and consumes messages in real-time.
## 📌 Features
- Spring Boot integration with Apache Kafka 
- Clean separation between producer and consumer microservices 
- Simple string message payloads 
- Easy-to-follow configuration
## 🧪 How It Works
1. `pingmyserver` publishes a string message (e.g., `"Server is up"`). 
2. Kafka receives and routes the message to the appropriate topic.
3. `notification-service` listens to the topic and logs or processes the message.
