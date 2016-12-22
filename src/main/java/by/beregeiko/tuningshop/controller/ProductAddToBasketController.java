package by.beregeiko.tuningshop.controller;

import by.beregeiko.tuningshop.dao.ProductDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.dao.impl.ProductDaoMock;
import by.beregeiko.tuningshop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

/**
 * Created by Think on 12.12.2016.
 */
public class ProductAddToBasketController extends HttpServlet {
    private static final String PARAM_ID = "id";
    private static final String PRODUCTS_IN_BASKET = "productsInBasket";
    private static final String PAGE_ERROR = "error.jsp";

    private ProductDao productDao = new ProductDaoMock();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);

        if(!idStr.equals("")) {
            try{
                Integer id = Integer.valueOf(idStr);
                Product product = productDao.selectById(id);

                HttpSession session = req.getSession(true);
                Map<Product, Integer> oldBasket = (Map<Product, Integer>)session.getAttribute(PRODUCTS_IN_BASKET);
                if(oldBasket == null) {
                    session.setAttribute(PRODUCTS_IN_BASKET, singletonMap(product, 1));
                } else {
                    Map<Product, Integer> newBasket = new LinkedHashMap<>(oldBasket);
                    if(!newBasket.containsKey(product)) {
                        newBasket.put(product, 1);
                    } else {
                        newBasket.put(product,  newBasket.get(product) + 1);
                    }
                    session.setAttribute(PRODUCTS_IN_BASKET, newBasket);
                }
                //OK
                String referer = req.getHeader("referer"); // can make it using Filter
                resp.sendRedirect(referer);
                return;
            } catch (NumberFormatException | NoSuchEntityException | DaoSystemException ex){
                //NOP
            }
        }
        //FAIL
        resp.sendRedirect(PAGE_ERROR);
    }
}