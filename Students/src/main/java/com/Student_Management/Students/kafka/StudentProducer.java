package com.Student_Management.Students.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class StudentProducer {

private final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaTemplate<String, String> kafkaTemplate;

    public StudentProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStudent(StudentKafkaDTO student) {

        try {

            String json = objectMapper.writeValueAsString(student);

            kafkaTemplate.send("student-created", json);

            System.out.println("Student event sent: " + json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}