package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {

    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource){this.dataSource = dataSource;}

    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, saved_at, player_id) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setObject(2, state.getSavedAt());
            statement.setInt(3, state.getPlayerId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void update(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE game_state SET current_map = ?, saved_at = ? WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setObject(2, state.getSavedAt());
            statement.setInt(3, state.getPlayerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(int id){
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT id, current_map, saved_at FROM game_state WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            GameState state = null;
            while (resultSet.next()) {
                state = new GameState(resultSet.getString("current_map"),
                        resultSet.getTimestamp("saved_at").toLocalDateTime(), id);
                state.setId(resultSet.getInt("id"));
            }
            return state;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
