package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.model.Car;
import spring.service.CarService;

@Controller
public class CarsController {

    @Autowired
    private CarService carService;

    @Value("${noSort}")
    private String noSort;

    @GetMapping("/cars")
    public String getByValue(@RequestParam(value = "count", required = false) String count,
                             @RequestParam(value = "sortBy", required = false) String sortBy,
                             Model model) {

        if (count == null & sortBy == null) {
            Iterable<Car> cars = carService.returnAllCars();
            model.addAttribute("cars", cars);
            return "cars";
        }
        if (sortBy == null) {
            Iterable<Car> cars = carService.getSpecifiedNumberCars(count);
            model.addAttribute("cars", cars);
            return "cars";
        }
        if (count == null) {
            if (sortBy.equals(noSort)) {
                return "errorPage";
            }
            Iterable<Car> cars = carService.sortCars(sortBy);
            model.addAttribute("cars", cars);
            return "cars";
        }
        if (sortBy.equals(noSort)) {
            return "errorPage";
        } else {
            Iterable<Car> cars = carService.getSortAndSpecifiedNumberCars(count, sortBy);
            model.addAttribute("cars", cars);
            return "cars";
        }
    }
}

