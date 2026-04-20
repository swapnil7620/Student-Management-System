/*package com.Student_Management.Students.kafka;

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

 */

package com.Student_Management.Students.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentProducer {

    private final KafkaTemplate<String, StudentKafkaDTO> kafkaTemplate;

    public StudentProducer(KafkaTemplate<String, StudentKafkaDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStudent(StudentKafkaDTO dto) {
        try {
            kafkaTemplate.send("student-created", dto);
            System.out.println("Sent event to Kafka: " + dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}