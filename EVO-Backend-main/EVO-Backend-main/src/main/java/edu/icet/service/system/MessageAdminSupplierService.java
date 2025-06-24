package edu.icet.service.system;
import edu.icet.dto.system.MessageAdminSupplier;

import java.util.List;

public interface MessageAdminSupplierService {
    MessageAdminSupplier sendMessage(MessageAdminSupplier message);
    List<MessageAdminSupplier> getMessagesBySupplierId(Long supplierId);
    List<MessageAdminSupplier> getMessagesByIds(Long adminId,Long supplierId);
    List<MessageAdminSupplier> getMessagesByAdminId(Long adminId);

}
