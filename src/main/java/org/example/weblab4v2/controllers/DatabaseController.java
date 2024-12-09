package org.example.weblab4v2.controllers;

import org.example.weblab4v2.classes.Database;
import org.example.weblab4v2.classes.Estate;
import org.example.weblab4v2.classes.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    private final Database db = new Database();
    private final Connection connection = db.getConnection();


    public List<User> user_read() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, mail FROM ro_users";
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("mail");
                User user = new User(id, name, email);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void user_create(String name, String email) {
        String sql = "INSERT INTO ro_users (name, mail) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void user_delete(long id) {
        String sql = "DELETE FROM ro_users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(long id) {
        String sql = "SELECT id, name, mail FROM ro_users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("mail")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void user_update(User user) {
        String sql = "UPDATE ro_users SET name = ?, mail = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getMail());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Estate> estate_read() {
        List<Estate> estateList = new ArrayList<>();
        String sql = "SELECT id, seller_id, title, cost, time FROM ro_estates";
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int sellerId = resultSet.getInt("seller_id");
                String title = resultSet.getString("title");
                double cost = resultSet.getDouble("cost");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                Estate estate = new Estate(id, sellerId, title, cost, time);
                estateList.add(estate);
            }
            return estateList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void estate_create(int sellerId, String title, double cost, LocalDateTime time) {
        String sql = """
                    INSERT INTO ro_estates (
                        seller_id, title, cost, time, realtor_id, accepted, archived, description, city_id, locality_id, type_id, area, bedrooms, floors
                    ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, sellerId);
            statement.setString(2, title);
            statement.setDouble(3, cost);
            statement.setTimestamp(4, Timestamp.valueOf(time));

            statement.setNull(5, java.sql.Types.INTEGER);
            statement.setNull(6, java.sql.Types.TIMESTAMP);
            statement.setInt(7, 1);
            statement.setString(8, "");
            statement.setInt(9, 1);
            statement.setInt(10, 1);
            statement.setInt(11, 1);
            statement.setDouble(12, 1.0);
            statement.setInt(13, 1);
            statement.setInt(14, 1);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void estate_delete(int id) {
        String sql = "DELETE FROM ro_estates WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Estate getEstateById(int id) {
        String sql = "SELECT id, seller_id, title, cost, time FROM ro_estates WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int sellerId = resultSet.getInt("seller_id");
                String title = resultSet.getString("title");
                double cost = resultSet.getDouble("cost");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                return new Estate(id, sellerId, title, cost, time);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void estate_update(Estate estate) {
        String sql = "UPDATE ro_estates SET seller_id = ?, title = ?, cost = ?, time = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, estate.getSellerId());
            statement.setString(2, estate.getTitle());
            statement.setDouble(3, estate.getCost());
            statement.setObject(4, estate.getTime());
            statement.setInt(5, estate.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> executeQuery(String queryString) {
        List<String[]> results = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(queryString);
             ResultSet resultSet = statement.executeQuery()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                results.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
