package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemsModel;
import com.codecool.dungeoncrawl.model.MonstersModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.io.InputStream;
import java.util.Scanner;

public class StateLoader {
    public static GameMap loadMapState(String mapName) {
        CellType defaultFloorCellType;

        if(mapName.equals("/map.txt")){
            defaultFloorCellType = CellType.FLOOR;
        }else{
            defaultFloorCellType = CellType.GRASS;
        }

        InputStream is = MapLoader.class.getResourceAsStream(mapName);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine();

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '*':
                            cell.setType(CellType.FENCE);
                            break;
                        case '.':
                        case 't':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                        case 'e':
                        case 'f':
                        case 'z':
                        case '@':
                        case 'k':
                        case 'S':
                        case 'H':
                        case 'D':
                        case 'm':
                        case 'p':
                        case 'c':
                        case 'g':
                        case 'ö':
                            cell.setType(defaultFloorCellType);
                            break;
                        case 'd':
                            cell.setType(CellType.DOOR);
                            map.setDoor(cell);
                            break;
                        case ',':
                            cell.setType(CellType.GRASS);
                            break;
                        case '|':
                            cell.setType(CellType.WALLFENCE);
                            break;
                        case 'w':
                            cell.setType(CellType.WATER);
                            break;
                        case 'q':
                            cell.setType(CellType.DIRT);
                            break;
                        case '1':
                            cell.setType(CellType.TREES);
                            break;
                        case '+':
                            cell.setType(CellType.STAIRS);
                            break;
                        case '-':
                            cell.setType(CellType.DOWNSTAIRS);
                            break;
                        case 'b':
                            cell.setType(CellType.BOSSFLOOR);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    public static void loadPlayerState(Player player, PlayerModel loadedPlayerModel, GameMap map, InventoryModel inventoryModel) {
        Cell playerCell = map.getCell(loadedPlayerModel.getX(), loadedPlayerModel.getY());
        player.setCell(playerCell);
        player.setName(loadedPlayerModel.getPlayerName());
        player.setHealth(loadedPlayerModel.getHp());
        player.setVision(loadedPlayerModel.getVision());
        player.setAttackStrength(loadedPlayerModel.getAttack());
        player.setDefenseStrength(loadedPlayerModel.getDefense());
        player.setInventory(loadInventory(inventoryModel));
        player.setAlive(true);
        map.setPlayer(player);
        playerCell.setActor(player);
    }

    private static Inventory loadInventory(InventoryModel inventoryModel){
        Inventory newInventory = new Inventory();
        for (String itemName : inventoryModel.getItems()){
            switch (itemName) {
                case "Key":
                    newInventory.addToInventory(new Key());
                    break;
                case "Sword":
                    newInventory.addToInventory(new Sword());
                    break;
                case "War hammer":
                    newInventory.addToInventory(new WarHammer());
                    break;
                case "Small shield":
                    newInventory.addToInventory(new SmallShield());
                    break;
                case "Medium shield":
                    newInventory.addToInventory(new MediumShield());
                    break;
                case "Potion":
                    newInventory.addToInventory(new Potion());
                    break;
                case "Potion cocktail":
                    newInventory.addToInventory(new PotionCocktail());
                    break;
                case "Torch":
                    newInventory.addToInventory(new Torch());
                    break;
                default:
                    throw new RuntimeException("Unrecognized inventory item: '" + itemName + "'");
            }
        }
        return newInventory;
    }

    public static void loadMapElements(ItemsModel items, MonstersModel monsters, GameMap map){
        for (ItemsModel.ItemPosition item : items.getItems()) {
            Cell cell = map.getCell(item.getX(), item.getY());
            switch (item.getName()) {
                case "key":
                    cell.setItem(new Key());
                    break;
                case "sword":
                    cell.setItem(new Sword());
                    break;
                case "war hammer":
                    cell.setItem(new WarHammer());
                    break;
                case "small shield":
                    cell.setItem(new SmallShield());
                    break;
                case "medium shield":
                    cell.setItem(new MediumShield());
                    break;
                case "potion":
                    cell.setItem(new Potion());
                    break;
                case "potion cocktail":
                    cell.setItem(new PotionCocktail());
                    break;
                case "torch":
                    cell.setItem(new Torch());
                    break;
                default:
                    throw new RuntimeException("Unrecognized item: '" + item.getName() + "'");
            }
        }
        for (MonstersModel.MonsterPosition monster : monsters.getMonsters()) {
            Cell cell = map.getCell(monster.getX(), monster.getY());
            switch (monster.getName()) {
                case "skeleton":
                    map.addMonster(new Skeleton(cell));
                    break;
                case "zombie":
                    map.addMonster(new Zombie(cell));
                    break;
                case "ghost":
                    map.addMonster(new Ghost(cell));
                    break;
                case "poltergeist":
                    map.addMonster(new Poltergeist(cell));
                    break;
                case "faceless":
                    map.addMonster(new Faceless(cell));
                    break;
                case "devil":
                    map.addMonster(new Devil(cell));
                    break;
                default:
                    throw new RuntimeException("Unrecognized monster: '" + monster.getName() + "'");
            }
        }
    }
}
