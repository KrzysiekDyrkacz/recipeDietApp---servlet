package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {

    private static final String FIND_ALL_DAY_NAME_QUERY = "SELECT * FROM day_name;";

    /**
     * Return all day_name
     *
     * @return
     */
    public List<DayName> findAll() {
        List<DayName> dayNamesList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_DAY_NAME_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DayName DayToAdd = new DayName();
                DayToAdd.setId(resultSet.getInt("id"));
                DayToAdd.setName(resultSet.getString("name"));
                DayToAdd.setDisplayOrder(resultSet.getInt("display_order"));

                dayNamesList.add(DayToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayNamesList;
    }

    /**
     * Converting day from String to int
     *
     * @param day
     * @return
     */
    public int convertDayToInt(String day) {
        if (day.equals("poniedziałek")) {
            return 1;
        } else if (day.equals("wtorek")) {
            return 2;
        } else if (day.equals("środa")) {
            return 3;
        } else if (day.equals("czwartek")) {
            return 4;
        } else if (day.equals("piątek")) {
            return 5;
        } else if (day.equals("sobota")) {
            return 6;
        } else if (day.equals("niedziela")) {
            return 7;
        }
        return 0;
    }


}