package ru.kpfu.itis.bagaviev.server;

import ru.kpfu.itis.bagaviev.dao.MasterDao;
import ru.kpfu.itis.bagaviev.dao.ServiceDao;
import ru.kpfu.itis.bagaviev.model.Master;
import ru.kpfu.itis.bagaviev.model.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "indexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MasterDao masterDao = new MasterDao();
        ServiceDao serviceDao = new ServiceDao();
        List<Master> masters = masterDao.getAll();
        List<Service> services = serviceDao.getAll();

        req.setAttribute("masters", masters);
        req.setAttribute("services", services);

        req.getRequestDispatcher("ftl/index.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
