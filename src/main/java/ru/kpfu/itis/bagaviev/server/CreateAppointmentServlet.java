package ru.kpfu.itis.bagaviev.server;

import ru.kpfu.itis.bagaviev.dao.AppointmentDao;
import ru.kpfu.itis.bagaviev.model.Appointment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "createAppointmentServlet", urlPatterns = "/create")
public class CreateAppointmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppointmentDao appointmentDao = new AppointmentDao();
        String phone = req.getParameter("phone");
        Integer masterId = Integer.parseInt(req.getParameter("master-id"));
        Integer serviceId = Integer.parseInt(req.getParameter("service-id"));

        String date = req.getParameter("appointment-date");
        String time = req.getParameter("appointment-time");

        Timestamp timestamp = Timestamp.valueOf(date + " " + time + "00:00");

        appointmentDao.save(new Appointment(
                masterId,
                serviceId,
                timestamp,
                phone
        ));
    }
}
