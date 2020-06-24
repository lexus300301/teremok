package dao;

import config.Config;
import domain.Admin;
import exception.DaoException;

import java.sql.*;

public class AdminDao {
    public static final String SELECT_ADMIN = "SELECT * FROM admins WHERE admin_login=?";
    public static final String INSERT_ADMIN = "INSERT INTO admins(" +
            " admin_login, admin_password)" +
            "VALUES ( ?, ?);";



    public Admin getAdmin(String login) throws DaoException {
        Admin result = new Admin();


        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ADMIN)
        ) {

            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result.setId(rs.getInt("admin_id"));
                result.setLogin(rs.getString("admin_login"));
                result.setPassword(rs.getString("admin_password"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        if (result.getLogin() == null && result.getPassword() == null) throw new DaoException("Admin doesnt exist");


        return result;
    }



    public void addAdmin(Admin admin) {
        Long result = -1L;
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_ADMIN)
        ) {
            stmt.setString(1, admin.getLogin());
            stmt.setString(2, admin.getPassword());
            ResultSet gkRs = stmt.getGeneratedKeys();
            if (gkRs.next()) {
                result = gkRs.getLong(1);
            }
            gkRs.close();
            stmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }




    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }
}
