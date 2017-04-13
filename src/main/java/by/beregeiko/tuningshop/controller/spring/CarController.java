package by.beregeiko.tuningshop.controller.spring;

import by.beregeiko.tuningshop.entity.Car;
import by.beregeiko.tuningshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Think on 27.02.2017.
 */
@Controller
public class CarController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ShopService shopService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String listBrands(Model model){
        logger.info("Listing cars");
        List<String> carBrands = shopService.findAllCarBrands();
        model.addAttribute("carbrands", carBrands);
        return "cars/listBrands";
    }

    @RequestMapping(value = "/cars{brand}", method = RequestMethod.GET)
    public String listCarModels(@PathVariable("brand") String brand, Model model){
        logger.info("Listing cars by brand");
        List<Car> cars = shopService.findCarByBrand(brand);
        model.addAttribute("cars", cars);
        logger.info("No. of car: " + cars.size());
        return "cars/listByBrand";
    }

    // TODO: add methods: createCar, updateCar

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }
}
