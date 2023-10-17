package ru.kpfu.itis.bagaviev.dao;

import ru.kpfu.itis.bagaviev.model.Appointment;
import ru.kpfu.itis.bagaviev.util.DatabaseConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao implements Dao<Appointment> {
    @Override
    public Appointment get(Integer id) {
        try (Statement statement = DatabaseConnectionUtil.getConnection().createStatement()) {
            String query = String.format("SELECT * FROM appointments WHERE appointments.id = '%d'", id);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new Appointment(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getTimestamp(4),
                        resultSet.getString(5)
                );
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return null;
    }

    @Override
    public List<Appointment> getAll() {
        List<Appointment> appointments = new ArrayList<>();
        try (Statement statement = DatabaseConnectionUtil.getConnection().createStatement()) {
            String query = "SELECT * FROM appointments";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getTimestamp(4),
                        resultSet.getString(5)
                ));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return appointments;
    }

    @Override
    public void save(Appointment appointment) {
        String query = "INSERT INTO appointments (master_id, service_id, time, phone) VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = DatabaseConnectionUtil.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, appointment.getMasterId());
            preparedStatement.setInt(2, appointment.getServiceId());
            preparedStatement.setTimestamp(3, appointment.getTime());
            preparedStatement.setString(4, appointment.getPhone());
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public boolean isAvailable(Appointment appointment) {
        String query = "SELECT * FROM appointments WHERE appointments.master_id = ? AND appointments.time = ?";
        try (PreparedStatement preparedStatement = DatabaseConnectionUtil.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, appointment.getMasterId());
            preparedStatement.setTimestamp(2, appointment.getTime());
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

}
