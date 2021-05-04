package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemsModel;
import com.codecool.dungeoncrawl.model.MonstersModel;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameState gameState;
    private GameStateDao gameStateDao;
    private PlayerModel model;
    private MonstersDao monstersDao;
    private ItemsDao itemsDao;
    private InventoryDao inventoryDao;
    private int playerId;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        monstersDao = new MonstersDaoJdbc(dataSource);
        itemsDao = new ItemsDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
    }

    public void savePlayer(Player player) {
        model = new PlayerModel(player);
        playerDao.add(model);
        playerId = model.getId();
    }

    public void saveInventory(Inventory inventory) {
        InventoryModel model = new InventoryModel(inventory);
        inventoryDao.add(model, playerId);
    }

    public void saveMonsters(GameMap map){
        MonstersModel monstersModel = new MonstersModel(map);
        monstersDao.add(monstersModel, gameState.getId());
    }

    public void saveItems(GameMap map) {
        ItemsModel itemsModel = new ItemsModel(map);
        itemsDao.add(itemsModel, gameState.getId());
    }

    public void updatePlayer(PlayerModel player) {
        playerDao.update(player);
    }

    public void getPlayer(Player player) {
//        playerDao.get(player.getId());
    }

    public void getAll() {
        playerDao.getAll();
    }

    public void saveGameState(String currentMap, LocalDateTime savedAt){
        GameState gs = new GameState(currentMap, savedAt, model);
        gameStateDao.add(gs);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
