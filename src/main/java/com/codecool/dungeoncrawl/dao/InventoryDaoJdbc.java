package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;

import javax.sql.DataSource;
import java.sql.*;
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
    public void update(InventoryModel inventory) {}

    @Override
    public InventoryModel get(int id) {
        return null;
    }

    @Override
    public List<InventoryModel> getAll() {
        return null;
    }
}
