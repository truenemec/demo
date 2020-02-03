package com.demo.productservice.common.service.impl;

import com.demo.productservice.common.service.HeartbeatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.Markers;
import org.springframework.boot.actuate.health.CompositeHealth;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class HeartbeatServiceImpl implements HeartbeatService {

    private final HealthEndpoint healthEndpoint;

    public void heartbeat() {
        HealthComponent healthComponent = healthEndpoint.health();
        Map<String, HealthComponent> components = healthComponent instanceof
                CompositeHealth ? ((CompositeHealth) healthComponent).getComponents() : Collections.emptyMap();
        log.info(Markers.append("heartbeat", healthComponent.getStatus()),
                "Heartbeat status = {} components = {}", healthComponent.getStatus(), components);
    }

}
