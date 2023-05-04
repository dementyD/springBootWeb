package spring.service;

import spring.model.Car;

public interface CarService {

    Iterable<Car> returnAllCars();
    Iterable<Car> getSpecifiedNumberCars(String count);
    Iterable<Car> sortCars(String sortBy);
    Iterable<Car> getSortAndSpecifiedNumberCars(String count, String sortBY);
}
