package edu.icet.service.system;

import edu.icet.dto.customer.User;
import edu.icet.dto.system.Reply;

import java.util.List;

public interface ReplyService {


    void addReply(Reply reply) throws IllegalArgumentException;

    List<Reply> getAllReplies();

    void updateReply(Reply reply);

    void deleteReplyById(Long replyId);

    Reply searchReplyById(Long replyId);

    Reply searchReplyByUserId(User user);

    boolean containsBadWords(String reply);

}
