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
    public void add(MonstersModel monsters) {
        try (Connection conn = dataSource.getConnection()) {
            for (MonstersModel.MonsterData monster : monsters.getMonsters()) {
                String sql = "INSERT INTO monsters (monster_name, x, y, game_id) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, monster.getName());
                statement.setInt(2, monster.getX());
                statement.setInt(3, monster.getY());
                statement.setInt(4, 100);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MonstersModel monsters) {

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
