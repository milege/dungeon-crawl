package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Faceless;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapName, CellType cellType) {
        InputStream is = MapLoader.class.getResourceAsStream(mapName);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

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
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(cellType);
                            map.addMonster(new Skeleton(cell));
                            break;
                        case '@':
                            cell.setType(cellType);
                            map.setPlayer(Player.getInstance(cell));
                            map.getPlayer().setCell(cell);
                            break;
                        case 'k':
                            cell.setType(cellType);
                            cell.setItem(new Key());
                            break;
                        case 'S':
                            cell.setType(cellType);
                            cell.setItem(new Sword());
                            break;
                        case 'D':
                            cell.setType(cellType);
                            cell.setItem(new Shield());
                            break;
                        case 'p':
                            cell.setType(cellType);
                            cell.setItem(new Potion());
                            break;
                        case 'g':
                            cell.setType(cellType);
                            map.addMonster(new Ghost(cell));
                            break;
                        case 'f':
                            cell.setType(cellType);
                            map.addMonster(new Faceless(cell));
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
                        case 't':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Torch());
                            break;
                        case '+':
                            cell.setType(CellType.STAIRS);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
