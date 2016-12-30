package by.beregeiko.tuningshop.filter;

import by.beregeiko.tuningshop.dao.CarDao;
import by.beregeiko.tuningshop.dao.CatalogDao;
import by.beregeiko.tuningshop.dao.exception.DaoSystemException;
import by.beregeiko.tuningshop.dao.impl.CarDaoMock;
import by.beregeiko.tuningshop.dao.impl.CatalogDaoMock;
import by.beregeiko.tuningshop.dao.impl.jdbc.CarDaoJbdcImpl;
import by.beregeiko.tuningshop.dao.impl.jdbc.CatalogDaoJdbcImpl;
import by.beregeiko.tuningshop.entity.Car;
import by.beregeiko.tuningshop.entity.Catalog;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Created by Think on 14.12.2016.
 */
public class AddAttributesToContextFilter extends BaseFilter {
    private static final String PAGE_ERROR = "error.jsp";
    private static final String ATTRIBUTE_CAR_LIST_TO_VIEW = "carList";
    private static final String ATTRIBUTE_CATALOG_LIST_TO_VIEW = "catalogList";

    private CatalogDao catalogDao = new CatalogDaoJdbcImpl();
    private CarDao carDao = new CarDaoJbdcImpl();

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        ServletContext servletContext = request.getServletContext();
        try {
            List<Catalog> catalogList = catalogDao.selectAll();
            List<Car> carList = carDao.selectAll();
            servletContext.setAttribute(ATTRIBUTE_CATALOG_LIST_TO_VIEW, catalogList);
            servletContext.setAttribute(ATTRIBUTE_CAR_LIST_TO_VIEW, carList);
            filterChain.doFilter(request, response);
            return;
        } catch (DaoSystemException e) {
            //NOP
        }
        // FAIL
        response.sendRedirect(PAGE_ERROR);
    }
}
