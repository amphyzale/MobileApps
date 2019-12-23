package com.enfor.myapp.carcrashmap.service;

import com.enfor.myapp.carcrashmap.domain.Message;
import com.enfor.myapp.carcrashmap.dto.MessageDto;

import java.util.List;

public interface MessageService {
    Message createMessage(MessageDto messageDto);
    List<MessageDto> getAllMessages();
    Message updateMessage(Message messageFromDb, Message message);
    void deleteMessage(Message message);
    MessageDto findById(Long id);
}
