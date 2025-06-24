package edu.icet.service.system.impl;

import edu.icet.dto.system.Chat;
import edu.icet.service.system.ChatService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Override
    public boolean create(Chat chat) {
        return false;
    }

    @Override
    public Chat getChat(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
