package ru.kpfu.itis.bagaviev.server;

import ru.kpfu.itis.bagaviev.dao.AppointmentDao;
import ru.kpfu.itis.bagaviev.model.Appointment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

@WebServlet(name = "checkAppointmentServlet", urlPatterns = "/check")
public class CheckAppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppointmentDao appointmentDao = new AppointmentDao();
        String phone = req.getParameter("phone");
        Integer masterId = Integer.parseInt(req.getParameter("master_id"));
        Integer serviceId = Integer.parseInt(req.getParameter("service_id"));

        String date = req.getParameter("appointment_date");
        String time = req.getParameter("appointment_time");

        Timestamp timestamp = Timestamp.valueOf(date + " " + time + "00:00");

        boolean result = appointmentDao.isAvailable(new Appointment(
                masterId,
                serviceId,
                timestamp,
                phone
        ));

        PrintWriter writer = resp.getWriter();
        writer.print(result ? "Date is available" : "Date is not available");
        writer.close();
    }

}
