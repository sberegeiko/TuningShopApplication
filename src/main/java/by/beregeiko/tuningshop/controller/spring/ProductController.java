package by.beregeiko.tuningshop.controller.spring;

import by.beregeiko.tuningshop.entity.Car;
import by.beregeiko.tuningshop.entity.Catalog;
import by.beregeiko.tuningshop.entity.Product;
import by.beregeiko.tuningshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Think on 27.02.2017.
 */
@Controller
@RequestMapping("/cars/{carId}")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ShopService shopService;

    @ModelAttribute("car")
    public Car loadCar(@PathVariable("carId") int carId) {
        return shopService.findCarById(carId);
    }

    @ModelAttribute("catalogs")
    public List<Catalog> loadCatalogs() {
        return shopService.findAllCatalogs();
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String listAllProducts(@PathVariable("carId") int carId, Model model){
        List<Product> products = shopService.findProductByCarId(carId);
        model.addAttribute("products", products);
        return "products/list";
    }

    @RequestMapping(value = "/catalogs/{catalogId}/products", method = RequestMethod.GET)
    public String listAllProductsByCatalog(@PathVariable("carId") int carId,
                                           @PathVariable("catalogId") int catalogId, Model model){
        List<Product> products = shopService.findProductByCarAndCatalogId(carId, catalogId);
        model.addAttribute("products", products);
        return "products/list";
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    public ModelAndView showProduct(@PathVariable("productId") int productId){
        ModelAndView mav = new ModelAndView("products/productDetails");
        mav.addObject(this.shopService.findProductById(productId));
        return mav;
    }

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }
}
