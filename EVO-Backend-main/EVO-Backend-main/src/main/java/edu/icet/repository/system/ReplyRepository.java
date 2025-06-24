package edu.icet.repository.system;

import edu.icet.dto.customer.User;
import edu.icet.entity.system.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    List<ReplyEntity> findByUser(User user);
}
