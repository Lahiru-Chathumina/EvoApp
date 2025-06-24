package edu.icet.service.admin.impl;

import edu.icet.dto.admin.Admin;
import edu.icet.entity.admin.AdminEntity;
import edu.icet.repository.admin.AdminRepository;
import edu.icet.service.admin.AdminService;
import edu.icet.util.AdminType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper mapper;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean addAdmin(Admin admin) {
        AdminEntity save = adminRepository.save(mapper.map(admin, AdminEntity.class));
        try{
            return adminRepository.existsById(save.getId());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteAdmin(Long adminID) {
        if (adminRepository.existsById(adminID)){
            adminRepository.deleteById(adminID);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAdmin(Long adminID, Admin admin) {
        if (adminRepository.existsById(adminID)) {
            AdminEntity save = adminRepository.save(mapper.map(admin, AdminEntity.class));
            return true;
        }
        return false;
    }

    @Override
    public Admin getAdminById(Long adminID) {
       return mapper.map(adminRepository.findById(adminID), Admin.class);
    }


    @Override
    public boolean adminExists(Long adminID) {
       return adminRepository.existsById(adminID);
    }
    @Override
    public Long countAdmins() {
      return adminRepository.count();
    }


    public boolean changeAdminType(Long adminID, AdminType type) {
        return adminRepository.findById(adminID).map(adminEntity -> {
             adminEntity.setType(type);
             adminRepository.save(adminEntity);
             return true;
         }).orElse (false);
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<AdminEntity> all = adminRepository.findAll();

        List<Admin> adminList=new ArrayList<>();
        all.forEach(admin ->{
           adminList.add(mapper.map(admin,Admin.class));
        });
        return adminList;
    }

    @Override
    public List<Admin> getAdminByType(AdminType type) {
        List<Admin> adminList=new ArrayList<>();

       adminRepository.findAllByType(type).forEach(admin -> {
           adminList.add(mapper.map(admin,Admin.class));
        });
        return adminList;
    }

    @Override
    public boolean existsByEmail(String email) {
        try{
            return adminRepository.existsByEmail(email);
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Admin getCustomerByEmail (String email) {
        return this.mapper.map(this.adminRepository.findByEmail(email), Admin.class);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return modelMapper.map(adminRepository.findByEmail(email), Admin.class);
    }
}


