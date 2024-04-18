package com.bing.rabbitmqtest.middleware.config;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareErrorHandler;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.messaging.Message;

import javax.annotation.Resource;

/**
 * kafka配置信息加载
 * @author sunyibing
 * @date 2024/4/18
 */
@Configuration
public class KafkaConfig {

    /**
     * kafka异常处理   通过@KafkaListener 中的errorHandler引入 ConsumerAwareListenerErrorHandler 实例
     * @KafkaListener(topics = {"sb_topic"},errorHandler = "consumerAwareErrorHandler")
     * @return
     */
    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareListenerErrorHandler (){
        return new ConsumerAwareListenerErrorHandler() {
            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
                System.out.println("消费异常：" + message.getPayload());
                return null;
            }
        };
    }


    @Resource
    ConsumerFactory consumerFactory;

    /**
     * kafka消息过滤器  通过@KafkaListener 中的 containerFactory
     *
     * @KafkaListener(topics = {"sb_topic"},containerFactory = "filterContainerFactory")
     */

    @Bean
    public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        //被过滤的消息将被丢弃
        factory.setAckDiscarded(true);
        //消息过滤策略
        factory.setRecordFilterStrategy(new RecordFilterStrategy() {
            @Override
            public boolean filter(ConsumerRecord consumerRecord) {
                // 过滤逻辑  todo   例如  过滤掉奇数
                if (Integer.parseInt(consumerRecord.value().toString()) % 2 == 0) {
                    return false;
                }
                return true;
            }
        });
        return factory;
    }

}
