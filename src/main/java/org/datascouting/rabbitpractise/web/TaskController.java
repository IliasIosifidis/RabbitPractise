package org.datascouting.rabbitpractise.web;

import lombok.RequiredArgsConstructor;
import org.datascouting.rabbitpractise.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController {

  private final RabbitTemplate rabbitTemplate;

  @PostMapping("/tasks")
  @ResponseStatus(HttpStatus.ACCEPTED) // not DONE, but accepted and queued, it will be done async later
  public String submitTask(@RequestBody String payload){
    rabbitTemplate.convertAndSend(RabbitConfig.SLOW_TASK_QUEUE, payload);
    System.out.println("PUBLISHED: " + payload);
    return "queued";
  }

  @PostMapping("/orders")
  public String submitOrder(@RequestBody String payload){
    rabbitTemplate.convertAndSend(RabbitConfig.ORDER_EXCHANGE, "", payload);
    System.out.println("ORDER PUBLISHED: " + payload);
    return "order accepted";
  }
}
