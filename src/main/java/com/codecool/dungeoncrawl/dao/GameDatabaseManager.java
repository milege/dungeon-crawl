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
import java.util.List;


public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameState gameState;
    private GameStateDao gameStateDao;
    private PlayerModel model;
    private MonstersDao monstersDao;
    private ItemsDao itemsDao;
    private InventoryDao inventoryDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        monstersDao = new MonstersDaoJdbc(dataSource);
        itemsDao = new ItemsDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
    }

    public void saveGame(Player player, String currentMap, GameMap map) {
        savePlayer(player);
        LocalDateTime localDate = LocalDateTime.now();
        saveGameState(currentMap, localDate);
        saveMonsters(map);
        saveItems(map);
        saveInventory(player.getInventory());
    }

    private void savePlayer(Player player) {
        model = new PlayerModel(player);
        playerDao.add(model);
    }

    private void saveInventory(Inventory inventory) {
        InventoryModel inventoryModel = new InventoryModel(inventory);
        inventoryDao.add(inventoryModel, model.getId());
    }

    private void saveMonsters(GameMap map){
        MonstersModel monstersModel = new MonstersModel(map);
        monstersDao.add(monstersModel, gameState.getId());
    }

    private void saveItems(GameMap map) {
        ItemsModel itemsModel = new ItemsModel(map);
        itemsDao.add(itemsModel, gameState.getId());
    }

    private void saveGameState(String currentMap, LocalDateTime savedAt){
        gameState = new GameState(currentMap, savedAt, model.getId());
        gameStateDao.add(gameState);
    }

    public PlayerModel getPlayer(int id) {
        return playerDao.get(id);
    }

    public InventoryModel getInventory(int id) {
        return inventoryDao.get(id);
    }

    public MonstersModel getMonsters(int id) {
        return monstersDao.get(id);
    }

    public ItemsModel getItems(int id){
        return itemsDao.get(id);
    }

    public GameState getGameState(int id) {
        return gameStateDao.get(id);
    }

    public List<PlayerModel> getAll() {
        return playerDao.getAll();
    }

    public boolean saveExist() {
        return false;
    }

    public void updateSave(Player player, String currentMap, GameMap map) {

    }

    private void updatePlayer(PlayerModel player) {
        playerDao.update(player);
    }

    private void updateGameState(GameState gameState){
        gameStateDao.update(gameState);
    }

    private void updateInventory(InventoryModel inventoryModel){
        inventoryDao.update(inventoryModel, model.getId());
    }

    private void updateMonsters(MonstersModel monstersModel){
        monstersDao.update(monstersModel, gameState.getId());
    }

    private void updateItems(ItemsModel itemsModel){
        itemsDao.update(itemsModel, gameState.getId());
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
