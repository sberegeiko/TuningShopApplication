package by.beregeiko.tuningshop.controller;

import by.beregeiko.tuningshop.dao.ProductDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Think on 12.12.2016.
 */
public class ProductController extends ApplicationContextServlet {
    private static final String PARAM_ID = "id";
    private static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    private static final String PAGE_OK = "productforselectedcar.jsp";
    private static final String PAGE_ERROR = "error.jsp";

    private ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        productDao = ctx.getBean("productDao", ProductDao.class);

        if(!idStr.equals("")) {
            try{
                Integer id = Integer.valueOf(idStr);
                Product model = productDao.selectById(id);
                req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (NumberFormatException | NoSuchEntityException | DaoSystemException ex){
                //NOP
            }
        }
        //FAIL
        resp.sendRedirect(PAGE_ERROR);
    }
}
