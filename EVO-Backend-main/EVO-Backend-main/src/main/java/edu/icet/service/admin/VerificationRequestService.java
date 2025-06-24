package edu.icet.service.admin;

import edu.icet.dto.admin.VerificationRequest;

import java.util.List;

public interface VerificationRequestService {

 boolean saveVerificationRequest(VerificationRequest request);
 VerificationRequest findVerificationrequestById(Long id);
 List<VerificationRequest> getAllVerificationRequest();
 boolean deleteVerificationRequest(Long id);
 boolean updateVerificationRequest(Long requestID,VerificationRequest request);
}
