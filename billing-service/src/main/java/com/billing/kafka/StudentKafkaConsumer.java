package com.billing.kafka;



import com.billing.dto.StudentEventDTO;
import com.billing.service.BillingService;
import tools.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StudentKafkaConsumer {

    private final BillingService billingService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public StudentKafkaConsumer(BillingService billingService) {
        this.billingService = billingService;
    }

    @KafkaListener(topics = "student-created", groupId = "billing-group")
    public void consume(String message) {

        try {

            System.out.println("Received Event From Kafka: " + message);

            if (message == null || message.trim().isEmpty() || !message.trim().startsWith("{")) {
                System.out.println("Skipping invalid message: " + message);
                return;
            }

            StudentEventDTO dto =
                    objectMapper.readValue(message, StudentEventDTO.class);

            billingService.createBilling(dto);

        } catch (Exception e) {
            System.out.println("Invalid JSON received: " + message);
        }
    }
}