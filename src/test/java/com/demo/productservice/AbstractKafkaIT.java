package com.demo.productservice;


import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;


//@ActiveProfiles("test")
//@SpringBootTest//(classes = {KafkaMessageListener.class, KafkaConfigTest.class, KafkaAutoConfiguration.class})
//@EmbeddedKafka(
//        partitions = 1,
//        controlledShutdown = false//,
////        brokerProperties = {
////                "listeners=PLAINTEXT://localhost:3333",
////                "port=3333"
////        }
//        )
@Slf4j
public class AbstractKafkaIT {

//    @Autowired
//    private EmbeddedKafkaBroker kafkaEmbeded;
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

//    @MockBean
//    private KafkaMessageListener kafkaMessageListener;

//    @Mock
//    KafkaMessageListener kafkaMessageListener = mock(KafkaMessageListener.class);

//    @Test
//    public void testReceive() throws Exception {
//        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send("test", "ddd");
//        kafkaTemplate.flush();
//        result.get(5, TimeUnit.SECONDS);
//        log.info("ok");
//        //verify(kafkaMessageListener, timeout(3000)).listen(any(), any());
//    }
}