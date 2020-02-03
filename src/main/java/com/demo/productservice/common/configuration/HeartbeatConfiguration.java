package com.demo.productservice.common.configuration;

import com.demo.productservice.common.service.HeartbeatService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;


@AllArgsConstructor
@ConditionalOnProperty(name = "application.heartbeat.enabled", matchIfMissing = true)
public class HeartbeatConfiguration {

    private final HeartbeatService heartbeatService;

    @Scheduled(
            initialDelayString = "${application.heartbeat.initial-delay:30000}",
            fixedRateString = "${application.heartbeat.fixed-rate:30000}"
    )
    public void heartbeat(){
        heartbeatService.heartbeat();
    }
}
