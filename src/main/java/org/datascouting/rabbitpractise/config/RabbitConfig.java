package org.datascouting.rabbitpractise.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  public static final String SLOW_TASK_QUEUE = "slow.task.queue";
  public static final String ORDER_EXCHANGE = "order.events";
  public static final String EMAIL_QUEUE = "order.email.queue";
  public static final String INVENTORY_QUEUE = "order.inventory.queue";
  public static final String ANALYTICS_QUEUE = "order.analytics.queue";

  @Bean
  public Queue slowTaskQueue() {
    return new Queue(SLOW_TASK_QUEUE, true);
  }

  @Bean
  public FanoutExchange orderExchange() {
    return new FanoutExchange(ORDER_EXCHANGE, true, false);
  }

  @Bean
  public Queue emailQueue() {
    return new Queue(EMAIL_QUEUE, true);
  }

  @Bean
  public Queue inventoryQueue() {
    return new Queue(INVENTORY_QUEUE, true);
  }

  @Bean
  public Queue analyticsQueue() {
    return new Queue(ANALYTICS_QUEUE, true);
  }

  @Bean
  public Binding emailBinding() {
    return BindingBuilder.bind(emailQueue()).to(orderExchange());
  }

  @Bean
  public Binding inventoryBinding() {
    return BindingBuilder.bind(inventoryQueue()).to(orderExchange());
  }

  @Bean
  public Binding analyticsBinding() {
    return BindingBuilder.bind(analyticsQueue()).to(orderExchange());
  }
}