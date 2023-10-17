package ru.kpfu.itis.bagaviev.server;

import ru.kpfu.itis.bagaviev.dao.MastersServicesDao;
import ru.kpfu.itis.bagaviev.dao.ServiceDao;
import ru.kpfu.itis.bagaviev.model.MasterService;
import ru.kpfu.itis.bagaviev.model.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "findServicesServlet", urlPatterns = "/find")
public class FindServicesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("master_id"));

        MastersServicesDao mastersServicesDao = new MastersServicesDao();
        ServiceDao serviceDao = new ServiceDao();

        List<MasterService> mastersAndServices = mastersServicesDao.getAll();
        List<Integer> availableServiceIds = new ArrayList<>();
        List<Service> availableServices = new ArrayList<>();

        for (MasterService masterService : mastersAndServices) {
            if (id.equals(masterService.getMasterId())) {
                availableServiceIds.add(masterService.getServiceId());
            }
        }
        for (Integer serviceId : availableServiceIds) {
            availableServices.add(serviceDao.get(serviceId));
        }

        PrintWriter writer = resp.getWriter();
        for (Service service : availableServices) {
            writer.printf("<option id=\"%d\" name=\"%d\" value=\"%d\">%s<option>",
                    service.getId(),
                    service.getId(),
                    service.getId(),
                    service.getName());
        }
        writer.close();
    }
}
