package ru.kpfu.itis.bagaviev.dao;

import ru.kpfu.itis.bagaviev.model.Service;
import ru.kpfu.itis.bagaviev.util.DatabaseConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao implements Dao<Service> {
    @Override
    public Service get(Integer id) {
        try (Statement statement = DatabaseConnectionUtil.getConnection().createStatement()) {
            String query = String.format("SELECT * FROM services WHERE services.id = '%d'", id);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new Service(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return null;
    }

    @Override
    public List<Service> getAll() {
        List<Service> services = new ArrayList<>();
        try (Statement statement = DatabaseConnectionUtil.getConnection().createStatement()) {
            String query = "SELECT * FROM services";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                services.add(new Service(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return services;
    }

    @Override
    public void save(Service object) {

    }
}
