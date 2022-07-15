package com.course.consumer.consumer;

import com.course.consumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyPictureImageConsumer {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @RabbitListener(queues = "q.mypicture.image")
    public void listenEmployee(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){
        Picture picture =objectMapper.readValue(message, Picture.class);

        if(picture.getPageSize()>9000)
            //throw new AmqpRejectAndDontRequeueException("Picture size too large"+picture);
            channel.basicReject(tag,false);

        log.info("MyPictureImageConsumer is image {}",picture);
        channel.basicAck(tag,false);
    }

}
