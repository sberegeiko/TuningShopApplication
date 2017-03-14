package by.beregeiko.tuningshop.controller.spring;

import by.beregeiko.tuningshop.entity.Basket;
import by.beregeiko.tuningshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Think on 06.03.2017.
 */
@Controller
@RequestMapping("/basket")
@SessionAttributes("basket")
public class BasketController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ShopService shopService;

    @ModelAttribute("basket")
    public Basket loadBasket() {
        return new Basket();
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.GET)
    public String addToBasket(@RequestHeader("referer") String referer,
                              @ModelAttribute("basket") Basket basket,
                              @PathVariable("productId") int productId, Model model) {
        basket.addProduct(shopService.findProductById(productId));
        model.addAttribute("basket", basket);
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/subtract/{productId}", method = RequestMethod.GET)
    public String subtractFromBasket(@RequestHeader("referer") String referer,
                                     @ModelAttribute("basket") Basket basket,
                                     @PathVariable("productId") int productId, Model model) {
        basket.subtractProduct(shopService.findProductById(productId));
        model.addAttribute("basket", basket);
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.GET)
    public String removeFromBasket(@RequestHeader("referer") String referer,
                                   @ModelAttribute("basket") Basket basket,
                                   @PathVariable("productId") int productId, Model model) {
        basket.removeProduct(shopService.findProductById(productId));
        model.addAttribute("basket", basket);
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showBasket(@ModelAttribute("basket") Basket basket) {
        ModelAndView mav = new ModelAndView("basket/list");
        mav.addObject(basket);
        return mav;
    }

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }
}
