package edu.icet.service.system;

import edu.icet.dto.system.QuickReplies;

import java.util.List;

public interface QuickReplyService {

    Boolean save(QuickReplies quickReplies);
    Boolean delete(Long id);
    Boolean update (QuickReplies quickReplies);
    List<QuickReplies> getAll();
    Boolean filterProfanity(String content);

}
