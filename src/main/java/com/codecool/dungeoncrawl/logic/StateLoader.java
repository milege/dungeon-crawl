package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;
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
    public static void loadPlayerState(Player player, PlayerModel loadedPlayerModel, GameMap map, Inventory inventory) {
        Cell playerCell = map.getCell(loadedPlayerModel.getX(), loadedPlayerModel.getY());
        player.setCell(playerCell);
        player.setName(loadedPlayerModel.getPlayerName());
        player.setHealth(loadedPlayerModel.getHp());
        player.setVision(loadedPlayerModel.getVision());
        player.setAttackStrength(loadedPlayerModel.getAttack());
        player.setDefenseStrength(loadedPlayerModel.getDefense());
        player.setInventory(inventory);
        map.setPlayer(player);
        playerCell.setActor(player);
    }
}
