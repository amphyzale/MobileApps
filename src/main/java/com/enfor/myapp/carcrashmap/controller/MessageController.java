
package com.enfor.myapp.carcrashmap.controller;

import com.enfor.myapp.carcrashmap.domain.Message;
import com.enfor.myapp.carcrashmap.dto.MessageDto;
import com.enfor.myapp.carcrashmap.dto.UserDto;
import com.enfor.myapp.carcrashmap.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/message/")
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @GetMapping
    public ResponseEntity<List<MessageDto>> list() {
        List<MessageDto> result  = messageRepository.findAll()
                .stream()
                .map(MessageDto::fromMessage)
                .collect(Collectors.toList());

        return result.size() != 0 ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<MessageDto> getOne(@PathVariable("id") Message message) {
        MessageDto result = MessageDto.fromMessage(message);
        return result.getId() != null ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Message create(@RequestBody MessageDto message) {
        message.setCreated(LocalDateTime.now());
        return messageRepository.save(message.toMessage());
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb,
                          @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepository.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepository.delete(message);
    }

}

