package com.damiansiemieniec.logbucket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;

@Configuration
public class IntegrationConfig {
    @Bean
    public IntegrationFlow processUdpMessage() {
        return IntegrationFlows
                .from(new UnicastReceivingChannelAdapter(11111))
                .handle("udpController", "handleMessage")
                .get();
    }
}
