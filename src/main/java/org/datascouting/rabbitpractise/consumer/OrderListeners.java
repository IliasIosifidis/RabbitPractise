package org.datascouting.rabbitpractise.consumer;

import org.datascouting.rabbitpractise.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListeners {

  @RabbitListener(queues = RabbitConfig.EMAIL_QUEUE)
  public void sendEmail(String payload){
    System.out.println("📧 EMAIL: sending confirmation for " + payload);
  }

  @RabbitListener(queues = RabbitConfig.INVENTORY_QUEUE)
  public void updateInventory(String payload){
    System.out.println("📦 INVENTORY: decrementing stock for " + payload);
  }

  @RabbitListener(queues = RabbitConfig.ANALYTICS_QUEUE)
  public void logAnalytics(String payload){
    System.out.println("📊 ANALYTICS: recording event for " + payload);
  }
}
