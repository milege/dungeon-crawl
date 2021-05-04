package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name, hp, x, y, defense, attack, vision) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.setInt(5, player.getDefense());
            statement.setInt(6, player.getAttack());
            statement.setInt(7, player.getVision());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE player SET hp = ?, x = ?, y = ?, defense = ? , attack = ? , vision = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, player.getHp());
            statement.setInt(2, player.getX());
            statement.setInt(3, player.getY());
            statement.setInt(4, player.getDefense());
            statement.setInt(5, player.getAttack());
            statement.setInt(6, player.getVision());
            statement.setInt(7, player.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PlayerModel get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT player_name, hp, x, y, defense, attack, vision FROM player WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PlayerModel model = new PlayerModel(resultSet.getString("player_name"), resultSet.getInt("x"),
                        resultSet.getInt("y"));
                model.setDefense(resultSet.getInt("defense"));
                model.setAttack(resultSet.getInt("attack"));
                model.setVision(resultSet.getInt("vision"));
                model.setHp(resultSet.getInt("hp"));
                return model;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    @Override
    public List<PlayerModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM player";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.executeQuery();
            List<PlayerModel> players = new ArrayList<>();
            while (resultSet.next()) {
                PlayerModel model = new PlayerModel(resultSet.getString("player_name"), resultSet.getInt("x"),
                        resultSet.getInt("y"));
                model.setDefense(resultSet.getInt("defense"));
                model.setAttack(resultSet.getInt("attack"));
                model.setVision(resultSet.getInt("vision"));
                model.setHp(resultSet.getInt("hp"));
                players.add(model);
            }
            return players;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
