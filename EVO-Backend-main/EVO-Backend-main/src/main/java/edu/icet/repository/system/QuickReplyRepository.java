package edu.icet.repository.system;

import edu.icet.entity.system.QuickRepliesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuickReplyRepository extends JpaRepository<QuickRepliesEntity,Long> {

}
