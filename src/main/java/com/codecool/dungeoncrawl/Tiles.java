package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static final Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("fence", new Tile(5, 5));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("zombie", new Tile(30, 6));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("sword", new Tile(0, 30));
        tileMap.put("war hammer", new Tile(5, 29));
        tileMap.put("small shield", new Tile(5, 25));
        tileMap.put("medium shield", new Tile(8, 25));
        tileMap.put("potion", new Tile(25, 23));
        tileMap.put("potion cocktail", new Tile(26, 23));
        tileMap.put("ghost", new Tile(26, 6));
        tileMap.put("poltergeist", new Tile(28, 6));
        tileMap.put("faceless", new Tile(24, 8));
        tileMap.put("devil", new Tile(27, 2));
        tileMap.put("door", new Tile(11,11));
        tileMap.put("open door", new Tile(12,11));
        tileMap.put("corpse", new Tile(1, 14));
        tileMap.put("stairs", new Tile(3, 6));
        tileMap.put("trees", new Tile(3, 1));
        tileMap.put("grass", new Tile(5, 0));
        tileMap.put("wall fence", new Tile(2, 3));
        tileMap.put("water", new Tile(8, 5));
        tileMap.put("dirt", new Tile(1, 0));
        tileMap.put("downstairs", new Tile(2, 6));
        tileMap.put("torch", new Tile(4, 15));
        tileMap.put("boss floor", new Tile(6, 13));

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
