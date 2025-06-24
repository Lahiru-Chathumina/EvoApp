

package edu.icet.controller.system;

import edu.icet.dto.system.MessageAdminSupplier;
import edu.icet.service.system.MessageAdminSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/system/message/admin-supplier")
@RequiredArgsConstructor
@CrossOrigin
public class MessageAdminSupplierController {

    private final MessageAdminSupplierService messageService;

    @MessageMapping("/chat/admin-supplier/{supplierId}/{adminId}")
    @SendTo("/topic/chat/{supplierId}/{adminId}")
    public MessageAdminSupplier handleChatMessage(
            MessageAdminSupplier message,
            @DestinationVariable Long supplierId,
            @DestinationVariable Long adminId) {

        if (!supplierId.equals(message.getSupplierId()) || !adminId.equals(message.getAdminId())) {
            throw new IllegalArgumentException("Path variables don't match message content");
        }

        return messageService.sendMessage(message);
    }


    @GetMapping("/adminsBySupplierId")
    public ResponseEntity<List<String>> getAllAdminIds(Long supplierId) {
        List<MessageAdminSupplier> messages = messageService.getMessagesBySupplierId(supplierId);
        List<String> adminIds = messages.stream()
                .map(MessageAdminSupplier::getAdminId)
                .map(String::valueOf)
                .distinct()
                .collect(Collectors.toList());
        return ResponseEntity.ok(adminIds);
    }

    @GetMapping(value = "/suppliersByAdminId/{adminId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllSuppliersIds(@PathVariable Long adminId) {
        List<MessageAdminSupplier> messages = messageService.getMessagesByAdminId(adminId);
        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        List<String> supplierIds = messages.stream()
                .map(MessageAdminSupplier::getSupplierId)
                .map(String::valueOf)
                .distinct()
                .collect(Collectors.toList());
        return ResponseEntity.ok(supplierIds);
    }



    @GetMapping("/chat/{AdminId}/{supplierId}")
    public ResponseEntity<List<MessageAdminSupplier>> getAdminChat(@PathVariable Long AdminId, @PathVariable Long supplierId) {

        List<MessageAdminSupplier> messages = messageService.getMessagesByIds(AdminId, supplierId);
        System.out.println(messages.toString());
        return ResponseEntity.ok(messages);
    }

}

