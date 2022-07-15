package com.course.consumer.consumer;

import com.course.consumer.entity.Employee;
import com.course.consumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PictureImageConsumer {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @RabbitListener(queues = "q.picture.image")
    public void listenEmployee(String message){
        Picture picture =objectMapper.readValue(message, Picture.class);
        log.info("PictureImageConsumer is image {}",picture);
    }

}
