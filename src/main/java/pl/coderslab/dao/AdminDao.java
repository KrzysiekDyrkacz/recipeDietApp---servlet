package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final int STATUS_ENABLE = 1;
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name,last_name,email,password,superadmin,enable) VALUES (?,?,?,?,?,?);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id = ?;";
    private static final String FIND_ALL_ADMIN_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_BY_ID_QUERY = "SELECT * from admins where id = ?;";
    private static final String READ_ADMIN_BY_EMAIL_QUERY = "SELECT * from admins where email = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE   admins SET first_name = ? , last_name = ?, email = ? WHERE id = ?;";
    private static final String UPDATE_ADMIN_PASSWORD_QUERY = "UPDATE   admins SET password = ? WHERE id = ?;";
    private static final String UPDATE_ADMIN_ENABLE_QUERY = "UPDATE    admins SET enable = ? WHERE id = ?;";

    /**
     * Get admin by id
     *
     * @param id
     * @return
     */
    public Admin readById(Integer id) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPasswordString(resultSet.getString("password"));
                    admin.setSuperAdmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    /**
     * Get admin by email
     *
     * @param email
     * @return
     */
    public Admin readByEmail(String email) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_BY_EMAIL_QUERY)
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPasswordString(resultSet.getString("password"));
                    admin.setSuperAdmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    /**
     * Return all admins
     *
     * @return
     */
    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMIN_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin adminToAdd = new Admin();
                adminToAdd.setId(resultSet.getInt("id"));
                adminToAdd.setFirstName(resultSet.getString("first_name"));
                adminToAdd.setLastName(resultSet.getString("last_name"));
                adminToAdd.setEmail(resultSet.getString("email"));
                adminToAdd.setPasswordString(resultSet.getString("password"));
                adminToAdd.setSuperAdmin(resultSet.getInt("superadmin"));
                adminToAdd.setEnable(resultSet.getInt("enable"));
                adminList.add(adminToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

    /**
     * Create admin
     *
     * @param admin
     * @return
     */
    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_ADMIN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, admin.getFirstName());
            insertStm.setString(2, admin.getLastName());
            insertStm.setString(3, admin.getEmail());
            insertStm.setString(4, admin.getPassword());
            insertStm.setInt(5, admin.getSuperAdmin());
            insertStm.setInt(6, admin.getEnable());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
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


    /**
     * Remove admins by id
     *
     * @param id
     */
    public void deleteById(Integer id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Admin not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update admin
     *
     * @param admin
     */
    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            statement.setInt(4, admin.getId());
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Update password for admin
     *
     * @param admin
     */
    public void updatePassword(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_PASSWORD_QUERY)) {
            statement.setInt(2, admin.getId());
            statement.setString(1, admin.getPassword());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Update status online/offline
     *
     * @param id
     * @param enable
     */
    public void updateEnable(int id, int enable) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_ENABLE_QUERY)) {
            statement.setInt(2, id);
            statement.setInt(1, enable);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Checking email and password
     *
     * @param admin
     * @return
     */
    public boolean validationData(Admin admin) {
        List<Admin> allAdmins = findAll();
        for (Admin admins : allAdmins) {
            if (admins.getEmail().equals(admin.getEmail())) {
                if (admins.checkPassword(admin.getPassword(), admins.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checking if user is blocked by SuperAdmin
     *
     * @param id
     * @return
     */
    public boolean validationStatus(int id) {
        return readById(id).getEnable() == STATUS_ENABLE;
    }

    /**
     * Return all emails
     *
     * @return
     */
    public List<String> readEmails() {
        List<String> emails = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMIN_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                emails.add(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }
}