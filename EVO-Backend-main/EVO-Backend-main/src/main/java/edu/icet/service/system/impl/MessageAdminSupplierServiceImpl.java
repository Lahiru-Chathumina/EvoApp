package edu.icet.service.system.impl;

import edu.icet.dto.system.MessageAdminSupplier;
import edu.icet.entity.system.MessageAdminSupplierEntity;
import edu.icet.repository.system.MessageAdminSupplierRepository;
import edu.icet.service.system.MessageAdminSupplierService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageAdminSupplierServiceImpl implements MessageAdminSupplierService {
    private final MessageAdminSupplierRepository messageAdminSupplierRepository;
    private final ModelMapper mapper;



    @Override
    public MessageAdminSupplier sendMessage(MessageAdminSupplier dto) {
        dto.setSendTime((Instant.now()));

        MessageAdminSupplierEntity entity = mapper.map(dto, MessageAdminSupplierEntity.class);
        MessageAdminSupplierEntity savedEntity = messageAdminSupplierRepository.save(entity);
        return mapper.map(savedEntity, MessageAdminSupplier.class);
    }




    @Override
    public List<MessageAdminSupplier> getMessagesBySupplierId(Long supplierId) {
        ArrayList<MessageAdminSupplier> list = new ArrayList<>();
        messageAdminSupplierRepository.findBySupplierId(supplierId).forEach(messageAdminSupplierEntity -> list.add(mapper.map(messageAdminSupplierEntity, MessageAdminSupplier.class)));
        return list;

    }

    @Override
    public List<MessageAdminSupplier> getMessagesByIds(Long adminId, Long supplierId) {
        return messageAdminSupplierRepository.findByAdminIdAndSupplierId(adminId, supplierId)
                .stream()
                .map((MessageAdminSupplierEntity entity) -> mapper.map(entity, MessageAdminSupplier.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<MessageAdminSupplier> getMessagesByAdminId(Long adminId) {
        return Optional.ofNullable(messageAdminSupplierRepository.findByAdminId(adminId))
                .orElse(Collections.emptyList()) // Ensures no null value
                .stream()
                .map(entity -> mapper.map(entity, MessageAdminSupplier.class))
                .collect(Collectors.toList());
    }




}

