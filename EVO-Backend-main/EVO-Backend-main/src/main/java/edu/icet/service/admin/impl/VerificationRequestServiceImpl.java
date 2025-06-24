package edu.icet.service.admin.impl;

import edu.icet.dto.admin.VerificationRequest;
import edu.icet.entity.admin.VerificationRequestEntity;
import edu.icet.repository.admin.VerificationRequestRepository;
import edu.icet.service.admin.VerificationRequestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository repository;
    private final ModelMapper mapper;

    @Override
    public boolean saveVerificationRequest(VerificationRequest request) {
        if (request == null) {
            return false;
        }
        repository.save(mapper.map(request, VerificationRequestEntity.class));
        return true;
    }

    @Override
    public VerificationRequest findVerificationrequestById(Long id) {
        if (id == null){
            return null;
        }
        if (!repository.existsById(id)){
            return null;
        }
        return mapper.map(repository.findById(id), VerificationRequest.class);
    }

    @Override
    public List<VerificationRequest> getAllVerificationRequest() {
        List<VerificationRequest> verificationRequestList = new ArrayList<>();
        List<VerificationRequestEntity> all = repository.findAll();

        all.forEach(verificationRequestEntity -> {
            verificationRequestList.add(mapper.map(verificationRequestEntity, VerificationRequest.class));
        });

        return verificationRequestList;
    }

    @Override
    public boolean deleteVerificationRequest(Long id) {
        if (id == null){
            return false;
        }
        if (!repository.existsById(id)){
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateVerificationRequest(Long id, VerificationRequest request) {
        if (id == null) {
            return false;
        }
        if (!repository.existsById(id)) {
            return false;
        }
        repository.save(mapper.map(request, VerificationRequestEntity.class));
        return true;
    }
}

