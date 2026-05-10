package org.example.aggregatorservice.config;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebConfigClient {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create()
                                /* TCP connection level timeout*/
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                                /* HTTP response level timeout */
                                .responseTimeout(Duration.ofSeconds(2))
                ))
                .build();
    }
}
