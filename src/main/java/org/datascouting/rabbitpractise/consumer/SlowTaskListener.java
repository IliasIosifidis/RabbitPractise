package org.datascouting.rabbitpractise.consumer;

import org.datascouting.rabbitpractise.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SlowTaskListener {

//  @RabbitListener(queues = RabbitConfig.SLOW_TASK_QUEUE)
//  public void handleTaskA(String payload) throws InterruptedException {
//    System.out.println("[A] STARTED " + payload);
//    Thread.sleep(1000);
//    System.out.println("[b] DONE: " + payload);
//  }

  @RabbitListener(queues = RabbitConfig.SLOW_TASK_QUEUE)
  public void handleTaskC(String payload) {
    System.out.println("[C] RECEIVED " + payload);
    throw new RuntimeException("simulated failure");
  }

//  @RabbitListener(queues = RabbitConfig.SLOW_TASK_QUEUE)
//  public void handleTaskB(String payload) throws InterruptedException {
//    System.out.println("[B] RECEIVED " + payload + " -processing...");
//    Thread.sleep(500);
//    System.out.println("[B] DONE: " + payload);
//  }
}
