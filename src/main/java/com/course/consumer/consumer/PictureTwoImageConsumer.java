package com.course.consumer.consumer;

import com.course.consumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PictureTwoImageConsumer {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @RabbitListener(queues = {"q.picture.image", "q.picture.vector", "q.picture.log", "q.picture.filter"})
    public void listenEmployee(Message message) {

        Picture picture = objectMapper.readValue(message.getBody(), Picture.class);
        log.info("PictureTwoImageConsumer is image {} with routing key: {}",
                picture,
                message.getMessageProperties().getReceivedRoutingKey());
    }

}
