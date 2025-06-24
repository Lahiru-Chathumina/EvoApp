package edu.icet.service.supplier;

import reactor.core.publisher.Mono;

public interface WhatsappMessageService {
    Mono<String> sendNotification(Integer id);
    Mono<String> sendMessage(String message);
}
