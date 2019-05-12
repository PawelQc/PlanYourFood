package pl.qceyco.dao;

import org.apache.commons.lang3.StringUtils;
import pl.qceyco.model.Admin;
import pl.qceyco.utils.BCrypt;
import pl.qceyco.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final String CREATE_ADMIN = "INSERT INTO admins(first_name, last_name, email, password, superadmin, enable) VALUES (?,?,?,?,?,?);";
    private static final String UPDATE_ADMIN = "UPDATE admins SET first_name=?, last_name=?, email=?, password=?, superadmin=?, enable=? WHERE id = ?;";
    private static final String DELETE_ADMIN = "DELETE FROM admins WHERE id = ?;";
    private static final String GET_ALL_ADMINS = "SELECT * FROM admins;";
    private static final String GET_ADMIN_BY_ID = "SELECT * FROM admins WHERE id = ?;";


    public Admin createAdmin(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_ADMIN,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            setAdminData(admin, statement);
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute updateRecipe returned " + result);
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admin.setId(generatedKeys.getInt(1));
                    return admin;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setAdminData(Admin admin, PreparedStatement statement) throws SQLException {
        statement.setString(1, admin.getFirstName());
        statement.setString(2, admin.getLastName());
        statement.setString(3, admin.getEmail());
        statement.setString(4, admin.getPassword());
        statement.setInt(5, booleanToInt(admin.isSuperAdmin()));
        statement.setInt(6, booleanToInt(admin.isEnabledLogging()));
    }

    public void updateAdmin(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN)) {
            statement.setInt(7, admin.getId());
            setAdminData(admin, statement);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAdmin(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN)) {
            statement.setInt(1, admin.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Admin getAdminById(int adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ADMIN_BY_ID)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    getAdminData(admin, resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    private void getAdminData(Admin admin, ResultSet resultSet) throws SQLException {
        admin.setId(resultSet.getInt("id"));
        admin.setFirstName(resultSet.getString("first_name"));
        admin.setLastName(resultSet.getString("last_name"));
        admin.setEmail(resultSet.getString("email"));
        admin.setPasswordFromDB(resultSet.getString("password"));
        admin.setIsSuperAdmin(intToBoolean(resultSet.getInt("superadmin")));
        admin.setIsEnabledLogging(intToBoolean(resultSet.getInt("enable")));
    }

    public List<Admin> getAllAdmins() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_ADMINS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Admin admin = new Admin();
                getAdminData(admin, resultSet);
                adminList.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

    private int booleanToInt(boolean value) {
        if (value == true) {
            return 1;
        } else {
            return 0;
        }
    }

    private boolean intToBoolean(int value) {
        if (value == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Admin isAdmininDB(String email) {
        List<Admin> admins = getAllAdmins();
        Admin adminCheck = new Admin();
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email)) {
                adminCheck = admin;
            }
        }
        if (StringUtils.isBlank(adminCheck.getEmail())) {
            return null;
        }
        return adminCheck;
    }

    public boolean isPasswordCorrect(Admin admin, String password) {
        if (BCrypt.checkpw(password, admin.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}


