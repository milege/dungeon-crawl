package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapName, CellType defaultFloorCellType) {
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
                            cell.setType(defaultFloorCellType);
                            map.addMonster(new Skeleton(cell));
                            break;
                        case 'z':
                            cell.setType(defaultFloorCellType);
                            map.addMonster(new Zombie(cell));
                            break;
                        case '@':
                            cell.setType(defaultFloorCellType);
                            map.setPlayer(Player.getInstance(cell));
                            map.getPlayer().setCell(cell);
                            cell.setActor(map.getPlayer());
                            break;
                        case 'k':
                            cell.setType(defaultFloorCellType);
                            cell.setItem(new Key());
                            break;
                        case 'S':
                            cell.setType(defaultFloorCellType);
                            cell.setItem(new Sword());
                            break;
                        case 'H':
                            cell.setType(defaultFloorCellType);
                            cell.setItem(new WarHammer());
                            break;
                        case 'D':
                            cell.setType(defaultFloorCellType);
                            cell.setItem(new SmallShield());
                            break;
                        case 'm':
                            cell.setType(defaultFloorCellType);
                            cell.setItem(new MediumShield());
                            break;
                        case 'p':
                            cell.setType(defaultFloorCellType);
                            cell.setItem(new Potion());
                            break;
                        case 'c':
                            cell.setType(defaultFloorCellType);
                            cell.setItem(new PotionCocktail());
                            break;
                        case 'g':
                            cell.setType(defaultFloorCellType);
                            map.addMonster(new Ghost(cell));
                            break;
                        case 'ö':
                            cell.setType(defaultFloorCellType);
                            map.addMonster(new Poltergeist(cell));
                            break;
                        case 'f':
                            cell.setType(defaultFloorCellType);
                            map.addMonster(new Faceless(cell));
                            break;
                        case 'e':
                            cell.setType(defaultFloorCellType);
                            map.addMonster(new Devil(cell));
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
                        case 't':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Torch());
                            break;
                        case '+':
                            cell.setType(CellType.STAIRS);
                            break;
                        case '-':
                            cell.setType(CellType.DOWNSTAIRS);
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
