package edu.icet.controller.supplier;


import edu.icet.service.supplier.WhatsappMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/supplier/whatsapp")
@CrossOrigin
@RequiredArgsConstructor
public class WhatsappMessageController {
    private final WhatsappMessageService whatsappMessageService;

    @PostMapping("/message")
    public Mono<String> sendMessage(@RequestParam(value = "message") String message){
        return whatsappMessageService.sendMessage(message);
    }

    @PostMapping("/notification")
    public Mono<String> sendNotification(@RequestParam(name = "id") Integer id){
        return whatsappMessageService.sendNotification(id);
    }
}
