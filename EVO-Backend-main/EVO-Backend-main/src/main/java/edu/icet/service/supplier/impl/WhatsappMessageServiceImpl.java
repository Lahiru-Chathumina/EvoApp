package edu.icet.service.supplier.impl;

import edu.icet.service.supplier.WhatsappMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class WhatsappMessageServiceImpl implements WhatsappMessageService {

    private final WebClient webClient;
    @Override
    public Mono<String> sendNotification(Integer id) {
        String[] notifications = {
                "booking request",
                "cancel request",
                "resource updated"
        };
        if (id<=notifications.length && id>0){
            return sendMessage(notifications[id-1]);
        }
        return null;
    }

    @Override
    public Mono<String> sendMessage(String message) {
        String senuka="94719584198";
        String seniru="94767289630";

        Map<String, Object> requestBody = Map.of(
                "messaging_product", "whatsapp",
                "to", seniru,
                "type", "text",
                "text", Map.of("body", message)
        );

        return webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
    }
}
