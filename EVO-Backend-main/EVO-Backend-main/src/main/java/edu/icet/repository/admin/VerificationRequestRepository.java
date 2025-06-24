package edu.icet.repository.admin;

import edu.icet.entity.admin.VerificationRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRequestRepository extends JpaRepository<VerificationRequestEntity, Long> {
}
