package edu.icet.service.admin;
import edu.icet.dto.admin.Admin;
import edu.icet.util.AdminType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface AdminService {
     boolean addAdmin (Admin admin);
     boolean deleteAdmin (Long adminID);
     boolean updateAdmin(Long adminID,Admin admin);
     Admin getAdminById(Long adminID);
     boolean adminExists(Long adminID);
     Long countAdmins();
     List<Admin>getAllAdmins();
     List<Admin>getAdminByType(AdminType type);
     boolean existsByEmail(@NotBlank(message = "Email cannot be empty") @Email(message = "Invalid email format") String email);
     Admin getCustomerByEmail (String email);

     Admin getAdminByEmail(String email);
}
