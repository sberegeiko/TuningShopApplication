package by.beregeiko.tuningshop.controller;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by Think on 13.02.2017.
 */
public class ApplicationContextServlet extends HttpServlet {
    private static final String APP_CTX_PATH = "contextConfigLocation";
    protected static GenericXmlApplicationContext ctx;
    @Override
    public void init() throws ServletException {
        String appCtxPath = this.getServletContext().getInitParameter(APP_CTX_PATH);
        ctx = new GenericXmlApplicationContext();
        ctx.load(appCtxPath);
        ctx.refresh();
    }
}
