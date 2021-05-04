package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDaoJdbc implements InventoryDao {
    private DataSource dataSource;

    public InventoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(InventoryModel inventory, int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            for (String itemName : inventory.getItems()) {
                String sql = "INSERT INTO inventory (item_name, player_id) VALUES (?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, itemName);
                statement.setInt(2, playerId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(InventoryModel inventory, int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM inventory WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playerId);
            statement.executeUpdate();
            add(inventory, playerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> get(int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT item_name FROM inventory WHERE player_id = ?";
            List<String> result = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, playerId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
