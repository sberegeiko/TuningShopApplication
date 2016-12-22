package by.beregeiko.tuningshop.controller;

import by.beregeiko.tuningshop.dao.CarDao;
import by.beregeiko.tuningshop.dao.ProductDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.exception.NoSuchEntityException;
import by.beregeiko.tuningshop.dao.impl.CarDaoMock;
import by.beregeiko.tuningshop.dao.impl.ProductDaoMock;
import by.beregeiko.tuningshop.entity.Car;
import by.beregeiko.tuningshop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Think on 16.12.2016.
 */
public class ProductSelectedByCarAndCatalogController extends HttpServlet {
    private static final String PARAM_CAR_ID = "carId";
    private static final String PARAM_CATALOG_ID = "catalogId";

    private static final String ATTRIBUTE_PRODUCT_LIST_TO_VIEW = "productList";
    private static final String ATTRIBUTE_SELECTED_CAR = "selectedCar";
    private static final String PAGE_OK = "productsforselectedcar.jsp";
    private static final String PAGE_ERROR = "error.jsp";

    private CarDao carDao = new CarDaoMock();

    private ProductDao productDao = new ProductDaoMock();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carIdStr = req.getParameter(PARAM_CAR_ID);
        String catalogIdStr = req.getParameter(PARAM_CATALOG_ID);
        HttpSession session = req.getSession(true);
        try {
            if (!carIdStr.equals("")) {
                Integer carId = Integer.valueOf(carIdStr);
                Car selectedCar = carDao.selectById(carId);
                session.setAttribute(ATTRIBUTE_SELECTED_CAR, selectedCar);

                if (!catalogIdStr.equals("")) {
                    Integer catalogId = Integer.valueOf(catalogIdStr);
                    Set<Product> productList = productDao.selectByCarAndCatalogId(carId, catalogId);
                    req.setAttribute(ATTRIBUTE_PRODUCT_LIST_TO_VIEW, productList);
                    req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                    return;
                }

                List<Product> productList = productDao.selectByCarId(carId);
                req.setAttribute(ATTRIBUTE_PRODUCT_LIST_TO_VIEW, productList);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } else {
                if (!catalogIdStr.equals("")) {
                    session.setAttribute(ATTRIBUTE_SELECTED_CAR, null);
                    Integer catalogId = Integer.valueOf(catalogIdStr);
                    List<Product> productList = productDao.selectByCatalogId(catalogId);
                    req.setAttribute(ATTRIBUTE_PRODUCT_LIST_TO_VIEW, productList);
                    req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                    return;
                }
            }

            session.setAttribute(ATTRIBUTE_SELECTED_CAR, null);
            List<Product> productList = productDao.selectAll();
            req.setAttribute(ATTRIBUTE_PRODUCT_LIST_TO_VIEW, productList);
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            return;
        } catch (DaoSystemException | NoSuchEntityException ex) {
            // NOP
        }

        // FAIL
        resp.sendRedirect(PAGE_ERROR);
    }
}
