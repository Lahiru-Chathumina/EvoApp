package edu.icet.service.system.impl;

import edu.icet.dto.system.MessageCustomerSupplier;
import edu.icet.entity.system.MessageCustomerSupplierEntity;
import edu.icet.repository.system.MessageCustomerSupplierRepository;
import edu.icet.service.system.MessageCustomerSupplierService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageCustomerSupplierServiceImpl implements MessageCustomerSupplierService {
    private final MessageCustomerSupplierRepository messageRepository;
    private final ModelMapper mapper;


    @Override
    public MessageCustomerSupplier sendMessage(MessageCustomerSupplier message) {
        message.setSendTime((Instant.now()));
        MessageCustomerSupplierEntity messageCustomerSupplierEntity = messageRepository.save(mapper.map(message, MessageCustomerSupplierEntity.class));
        return mapper.map(messageCustomerSupplierEntity, MessageCustomerSupplier.class);
    }




    @Override
    public List<MessageCustomerSupplier> getMessagesBySupplierId(Long supplierId) {
        ArrayList<MessageCustomerSupplier> list = new ArrayList<>();
        messageRepository.findBySupplierId(supplierId).forEach(messageCustomerSupplierEntity -> list.add(mapper.map(messageCustomerSupplierEntity, MessageCustomerSupplier.class)));
        return list;
    }

    @Override
    public List<MessageCustomerSupplier> getMessagesByIds(Long customerId, Long supplierId) {
        return messageRepository.findByCustomerIdAndSupplierId(customerId, supplierId)
                .stream()
                .map((MessageCustomerSupplierEntity entity) -> mapper.map(entity, MessageCustomerSupplier.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageCustomerSupplier> getMessagesByCustomerId(Long customerId) {
        ArrayList<MessageCustomerSupplier> list = new ArrayList<>();
        messageRepository.findByCustomerId(customerId).forEach(messageCustomerSupplierEntity -> list.add(mapper.map(messageCustomerSupplierEntity, MessageCustomerSupplier.class)));
        return list;
    }


}
