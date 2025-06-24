package edu.icet.service.system;
import edu.icet.dto.system.MessageCustomerSupplier;

import java.util.List;

public interface MessageCustomerSupplierService {
    MessageCustomerSupplier sendMessage(MessageCustomerSupplier message);

    List<MessageCustomerSupplier> getMessagesBySupplierId(Long supplierId);
    List<MessageCustomerSupplier> getMessagesByIds(Long customerId,Long supplierId);
    List<MessageCustomerSupplier> getMessagesByCustomerId(Long customerId);
}
