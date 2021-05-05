package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MonstersModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class MonstersDaoJdbc implements MonstersDao {
    private DataSource dataSource;

    public MonstersDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(MonstersModel monsters, int gameId) {
        try (Connection conn = dataSource.getConnection()) {
            for (MonstersModel.MonsterPosition monster : monsters.getMonsters()) {
                String sql = "INSERT INTO monsters (monster_name, x, y, game_id) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, monster.getName());
                statement.setInt(2, monster.getX());
                statement.setInt(3, monster.getY());
                statement.setInt(4, gameId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MonstersModel monsters, int gameId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM monsters WHERE game_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, gameId);
            statement.executeUpdate();
            add(monsters, gameId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MonstersModel get(int id) {
        return null;
    }

    @Override
    public List<MonstersModel> getAll() {
        return null;
    }
}
