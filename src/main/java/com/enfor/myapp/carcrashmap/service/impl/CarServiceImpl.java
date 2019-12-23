package com.enfor.myapp.carcrashmap.service.impl;

import com.enfor.myapp.carcrashmap.domain.*;
import com.enfor.myapp.carcrashmap.dto.CarDto;
import com.enfor.myapp.carcrashmap.repository.*;
import com.enfor.myapp.carcrashmap.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private final BrandOfCarRepository brandOfCarRepository;

    private final ModelOfCarRepository modelOfCarRepository;

    private final TypeOfBodyRepository typeOfBodyRepository;

    private final TypeOfTransportRepository typeOfTransportRepository;

    private final CarRepository carRepository;

    public CarServiceImpl(BrandOfCarRepository brandOfCarRepository, ModelOfCarRepository modelOfCarRepository, TypeOfBodyRepository typeOfBodyRepository, TypeOfTransportRepository typeOfTransportRepository, CarRepository carRepository) {
        this.brandOfCarRepository = brandOfCarRepository;
        this.modelOfCarRepository = modelOfCarRepository;
        this.typeOfBodyRepository = typeOfBodyRepository;
        this.typeOfTransportRepository = typeOfTransportRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Car buildCar(CarDto carDto) {
        BrandOfCar brandOfCar = brandOfCarRepository.findByName(carDto.getBrandOfCar());
        ModelOfCar modelOfCar = modelOfCarRepository.findByName(carDto.getModelOfCar());
        TypeOfBody typeOfBody = typeOfBodyRepository.findByName(carDto.getTypeOfBody());
        TypeOfTransport typeOfTransport = typeOfTransportRepository.findByName(carDto.getTypeOfTransport());

        Car car = new Car(carDto.getRegNum(), brandOfCar, modelOfCar, typeOfBody, typeOfTransport);
        if (car.getRegNum() != null) {
            carRepository.save(car);
            return car;
        }

        return null;
    }
}
