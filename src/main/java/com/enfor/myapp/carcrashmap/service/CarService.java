package com.enfor.myapp.carcrashmap.service;

import com.enfor.myapp.carcrashmap.domain.Car;
import com.enfor.myapp.carcrashmap.dto.CarDto;

public interface CarService {
    Car buildCar(CarDto carDto);
}
