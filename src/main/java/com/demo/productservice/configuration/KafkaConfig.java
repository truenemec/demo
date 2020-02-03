package com.demo.productservice.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

//    @Bean
//    public ConsumerFactory<String, KafkaMessageListener.Test> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
//                new JsonDeserializer<>(KafkaMessageListener.Test.class));
//    }
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, KafkaMessageListener.Test> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, KafkaMessageListener.Test> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//
//        return factory;
//    }
//
//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "product-service-consumers");
//
//        return props;
//    }
}
