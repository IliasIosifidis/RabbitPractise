package org.datascouting.rabbitpractise.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  public static final String SLOW_TASK_QUEUE = "slow.task.queue";

  @Bean
  public Queue slowTaksQueue(){
    return new Queue(SLOW_TASK_QUEUE, true);
  }
}
