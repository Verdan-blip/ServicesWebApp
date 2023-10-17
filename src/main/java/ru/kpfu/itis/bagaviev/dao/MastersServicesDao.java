package ru.kpfu.itis.bagaviev.dao;

import ru.kpfu.itis.bagaviev.model.MasterService;
import ru.kpfu.itis.bagaviev.util.DatabaseConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MastersServicesDao implements Dao<MasterService> {
    @Override
    public MasterService get(Integer id) {
        return null;
    }

    @Override
    public List<MasterService> getAll() {
        List<MasterService> mastersAndServices = new ArrayList<>();
        try (Statement statement = DatabaseConnectionUtil.getConnection().createStatement()) {
            String query = "SELECT * FROM masters_services";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                mastersAndServices.add(
                  new MasterService(
                          resultSet.getInt(1),
                          resultSet.getInt(2),
                          resultSet.getInt(3)
                  )
                );
            }
            return mastersAndServices;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void save(MasterService object) {

    }

}
