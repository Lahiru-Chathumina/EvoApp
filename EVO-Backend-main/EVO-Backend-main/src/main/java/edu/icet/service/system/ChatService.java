package edu.icet.service.system;

import edu.icet.dto.system.Chat;

public interface ChatService {
    boolean create(Chat chat);

    Chat getChat(Integer id);

    boolean delete(Integer id);
}
