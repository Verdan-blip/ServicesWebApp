package ru.kpfu.itis.bagaviev.dao;

import ru.kpfu.itis.bagaviev.model.Master;
import ru.kpfu.itis.bagaviev.util.DatabaseConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MasterDao implements Dao<Master> {
    @Override
    public Master get(Integer id) {
        try (Statement statement = DatabaseConnectionUtil.getConnection().createStatement()) {
            String query = String.format("SELECT * FROM masters WHERE masters.id = '%d'", id);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new Master(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return null;
    }

    @Override
    public List<Master> getAll() {
        List<Master> masters = new ArrayList<>();
        try (Statement statement = DatabaseConnectionUtil.getConnection().createStatement()) {
            String query = "SELECT * FROM masters";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                masters.add(new Master(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return masters;
    }

    @Override
    public void save(Master object) {

    }
}
