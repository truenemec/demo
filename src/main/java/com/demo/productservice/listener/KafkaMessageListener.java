package com.demo.productservice.listener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class KafkaMessageListener {
    //@KafkaListener(topicPartitions = @TopicPartition(topic = "${service.topic}", partitionOffsets = @PartitionOffset(initialOffset = "0", partition = "0")))
//    @KafkaListener(topics = "${service.topic}")
//    public void listen(Test foo, MessageHeaders headers) {
//        log.info("Received: " + foo);
//    }

//    @KafkaListener(topics = "${service.topic}")
//    public void listen(String foo, MessageHeaders headers) {
//        log.info("Received: " + foo);
//    }
//
//    @Data
//    public static class Test{
//       private String code;
//       private String name;
//       private Set<String> values;
//    }

}
