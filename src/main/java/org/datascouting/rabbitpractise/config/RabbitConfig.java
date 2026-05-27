package org.datascouting.rabbitpractise.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  // Simulate a load
  public static final String SLOW_TASK_QUEUE = "slow.task.queue";
  // Add listeners (Fanout)
  public static final String ORDER_EXCHANGE = "order.events";
  public static final String EMAIL_QUEUE = "order.email.queue";
  public static final String INVENTORY_QUEUE = "order.inventory.queue";
  public static final String ANALYTICS_QUEUE = "order.analytics.queue";
  // Simulate dead-letter
  public static final String SLOW_TASK_DLX = "slow.task.dlx";
  public static final String SLOW_TASK_DLQ = "slow.task.dlq";

  // slow task
//  @Bean
//  public Queue slowTaskQueue() {
//    return new Queue(SLOW_TASK_QUEUE, true);
//  }

  //slow task reconfigure for dead-letter
  @Bean
  public Queue slowTaskQueue() {
    return QueueBuilder.durable(SLOW_TASK_QUEUE)
            .withArgument("x-dead-letter-exchange", SLOW_TASK_DLX)
            .build();
  }
  // dead-letter exchange + queue + binding
  @Bean
  public DirectExchange slowTaskDlx(){
    return new DirectExchange(SLOW_TASK_DLX, true, false);
  }

  @Bean
  public Queue slowTaskDlq(){
    return new Queue(SLOW_TASK_DLQ, true);
  }

  @Bean
  public Binding slowTaskDlqBinding(){
    return BindingBuilder.bind(slowTaskDlq()).to(slowTaskDlx()).with(SLOW_TASK_QUEUE);
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