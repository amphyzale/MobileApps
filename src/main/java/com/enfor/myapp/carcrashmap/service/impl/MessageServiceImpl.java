package com.enfor.myapp.carcrashmap.service.impl;

import com.enfor.myapp.carcrashmap.domain.Comments;
import com.enfor.myapp.carcrashmap.domain.Images;
import com.enfor.myapp.carcrashmap.domain.Message;
import com.enfor.myapp.carcrashmap.domain.Status;
import com.enfor.myapp.carcrashmap.dto.MessageDto;
import com.enfor.myapp.carcrashmap.repository.*;
import com.enfor.myapp.carcrashmap.service.CarService;
import com.enfor.myapp.carcrashmap.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final RoadObjectsRepository roadObjectsRepository;
    private final StreetsRepository streetsRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final CarService carService;
    private final MessageStatusesRepository messageStatusesRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,
                              RoadObjectsRepository roadObjectsRepository,
                              StreetsRepository streetsRepository,
                              UserDetailsRepository userDetailsRepository, CarService carService, MessageStatusesRepository messageStatusesRepository) {
        this.messageRepository = messageRepository;
        this.carService = carService;
        this.roadObjectsRepository = roadObjectsRepository;
        this.streetsRepository = streetsRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.messageStatusesRepository = messageStatusesRepository;
    }

    @Override
    public Message createMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setAuthor(userDetailsRepository.findByName(messageDto.getAuthor().getName()));
        message.setCar1(carService.buildCar(messageDto.getCar1()));
        message.setCar2(carService.buildCar(messageDto.getCar2()));
        message.setComments(messageDto.getComments().stream()
                .map(Comments::new)
                .collect(Collectors.toSet()));
        message.setDateOfCrash(messageDto.getDateOfCrash());
        message.setImages(messageDto.getImages().stream()
                .map(Images::new)
                .collect(Collectors.toSet()));
        message.setMessageStatus(message.getAuthor().getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals("ADMIN") ||
                        role.getName().equals("MODERATOR")) ?
                messageStatusesRepository.findById(3L) :
                messageStatusesRepository.findById(1L));
        message.setCreated(LocalDateTime.now());
        message.setTitle(messageDto.getTitle());
        message.setText(messageDto.getText());
        message.setTypeOfRoadObj(roadObjectsRepository.findByName(messageDto.getTypeOfRoadObj()));
        message.setX(messageDto.getX());
        message.setY(messageDto.getY());
        message.setUpdated(LocalDateTime.now());
        message.setStreet1(streetsRepository.findByName(messageDto.getStreet1()));
        message.setStreet2(streetsRepository.findByName(messageDto.getStreet2()));
        message.setStatus(Status.ACTIVE);
        return messageRepository.save(message);
    }

    @Override
    public List<MessageDto> getAllMessages() {
        return messageRepository.findAll()
                .stream()
                .filter(message -> message.getStatus() == Status.ACTIVE)
                .map(MessageDto::fromMessage)
                .collect(Collectors.toList());
    }

    @Override
    public Message updateMessage(Message messageFromDb, Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        messageFromDb.setUpdated(LocalDateTime.now());
        return messageRepository.save(messageFromDb);
    }

    @Override
    public void deleteMessage(Message message) {

    }

    @Override
    public MessageDto findById(Long id) {
        return null;
    }
}
