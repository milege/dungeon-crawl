package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemsModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDaoJdbc implements ItemsDao {
    private DataSource dataSource;

    public ItemsDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ItemsModel items, int gameId) {
        try (Connection conn = dataSource.getConnection()) {
            for (ItemsModel.ItemPosition item : items.getItems()) {
                String sql = "INSERT INTO items (item_name, x, y, game_id) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, item.getName());
                statement.setInt(2, item.getX());
                statement.setInt(3, item.getY());
                statement.setInt(4, gameId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(ItemsModel items, int gameId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM items WHERE game_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, gameId);
            statement.executeUpdate();
            add(items, gameId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemsModel get(int gameId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT item_name, x, y FROM items WHERE game_id = ?";
            List<ItemsModel.ItemPosition> itemPositions = new ArrayList<>();
            ItemsModel itemsModel = new ItemsModel();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                itemPositions.add(itemsModel.new ItemPosition(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3)));
            }
            itemsModel.setItems(itemPositions);
            return itemsModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
