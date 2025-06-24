package edu.icet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient getClient() {
        String token = "EAATG9ZBGIVhsBO2DyFDBA93QoFtsqNZBpGb9JcZCZCzwxpGhuXspXh8PuyspwnArP4Nt0ttq1yh9U4V6LRv6O97CGWGXi7BnsXJo28fDywX32CVkZCEcIHZA1prZAherWZC7wOrDnbScpWZATtwFQV5AjLkdMSQr6MjrWhGm7GfPtyyXyC5o0t1p2ksWRpYaWG2BlNhLo03bwKG0ZC4CZC2wdh3LENZBXm4ZD";

        return WebClient.builder()
                .baseUrl("https://graph.facebook.com/v18.0/570833919447316/messages")
                .defaultHeader("Authorization", "Bearer " + token)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
