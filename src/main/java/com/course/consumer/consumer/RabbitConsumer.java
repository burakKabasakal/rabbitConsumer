package com.course.consumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

//@Service
public class RabbitConsumer {

    //@RabbitListener(queues = "course.hello")
    public void listen(String message){
        System.out.println("Message: "+message);
    }

    @RabbitListener(queues = "course.fixedrate", concurrency = "3-7")
    public void listenFixedRate(String message) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(1000,2000));
        System.out.println("Thread Name: "+Thread.currentThread().getName()+"Message: "+message);
    }
}
