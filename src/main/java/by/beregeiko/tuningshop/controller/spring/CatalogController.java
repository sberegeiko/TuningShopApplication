package by.beregeiko.tuningshop.controller.spring;

import by.beregeiko.tuningshop.entity.Catalog;
import by.beregeiko.tuningshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Think on 27.02.2017.
 */
@Controller
@RequestMapping("/catalogs")
public class CatalogController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ShopService shopService;

    @RequestMapping(value = "/catalogs", method = RequestMethod.GET)
    public String list(Model model){
        logger.info("Listing catalogs");
        List<Catalog> catalogs = shopService.findAllCatalogs();
        model.addAttribute("catalogs", catalogs);
        logger.info("No. of catalogs: " + catalogs.size());
        return "catalogs/list";
    }

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }
}
