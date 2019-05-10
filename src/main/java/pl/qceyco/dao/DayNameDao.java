package pl.qceyco.dao;

import pl.qceyco.model.DayName;
import pl.qceyco.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DayNameDao {

    private static final String GET_ALL_DAYS = "SELECT * FROM day_name;";
    private static final String GET_DAY_BY_ID = "SELECT * FROM day_name WHERE id = ?;";

    public List<DayName> getAllDays() {
        List<DayName> dayList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_DAYS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                DayName dayToAdd = new DayName();
                dayToAdd.setId(resultSet.getInt("id"));
                dayToAdd.setName(resultSet.getString("name"));
                dayToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                dayList.add(dayToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayList;
    }

    public DayName getDayNameById(int dayNameId) {
        DayName dayName = new DayName();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_DAY_BY_ID)) {
            statement.setInt(1, dayNameId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    dayName.setId(resultSet.getInt("id"));
                    dayName.setName(resultSet.getString("name"));
                    dayName.setDisplayOrder(resultSet.getInt("display_order"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayName;
    }

}
