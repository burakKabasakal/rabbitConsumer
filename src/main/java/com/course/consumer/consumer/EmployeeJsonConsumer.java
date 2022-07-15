package com.course.consumer.consumer;

import com.course.consumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeJsonConsumer {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @RabbitListener(queues = "course.employee")
    public void listenEmployee(String message){
        Employee employee =objectMapper.readValue(message, Employee.class);
        log.info("Employee: {}",employee);
    }

}
