package spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import spring.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.repositories.CarRepository;

@Service
@PropertySource("car.properties")
public class CarServiceImp implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Value("${maxCar}")
    private int maxCar;

    @Override
    public Iterable<Car> returnAllCars() {
        Iterable<Car> cars = carRepository.findAll();
        return cars;
    }

    @Override
    public Iterable<Car> getSpecifiedNumberCars(String count) {
        int countValue = Integer.parseInt(count);
        if (countValue >= maxCar) {
            Iterable<Car> carsAll = carRepository.findAll();
            return carsAll;
        }
        Iterable<Car> cars = carRepository.getCarLimit(countValue);
        // 2й вариант   Iterable<Car> cars = carRepository.findAll(PageRequest.of(0, countValue));
        return cars;
    }

    @Override
    public Iterable<Car> sortCars(String sortBy) {
        Iterable<Car> cars = carRepository.findAll(Sort.by(sortBy));
        return cars;
    }

    @Override
    public Iterable<Car> getSortAndSpecifiedNumberCars(String count, String sortBY) {
        int countValue = Integer.parseInt(count);
        Iterable<Car> cars = carRepository.findAll(PageRequest.of(0, countValue, Sort.by(sortBY)));
        return cars;
    }
}
