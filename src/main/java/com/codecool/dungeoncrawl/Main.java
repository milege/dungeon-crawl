package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label attackLabel = new Label();
    Label defenseLabel = new Label();
    Label inventoryLabel = new Label();
    Button itemPickUpButton = new Button("Pick up item");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Attack: "), 0, 1);
        ui.add(attackLabel, 1, 1);
        ui.add(new Label("Defense: "), 0, 2);
        ui.add(defenseLabel, 1, 2);
        ui.add(inventoryLabel, 0, 3);
        ui.add(itemPickUpButton,0,4);


        itemPickUpButton.setOnAction(onClick -> {
            map.getPlayer().pickUpItem();
            ui.requestFocus();
            refresh();
        });

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        ui.requestFocus();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                map.moveMonsters();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                map.moveMonsters();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                map.moveMonsters();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                map.moveMonsters();
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int playerCellX = map.getPlayer().getCell().getX();
        int playerCellY = map.getPlayer().getCell().getY();
        int mapCenterX = map.getWidth() / 2;
        int mapCenterY = map.getHeight() / 2;
        int visionRadius = map.getPlayer().getVision();

        for (int x = -visionRadius ; x <= visionRadius; x++) {
            for (int y = -visionRadius; y <= visionRadius; y++) {
                try {
                    Cell cell = map.getCell(playerCellX + x, playerCellY + y);
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), mapCenterX + x, mapCenterY + y);
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), mapCenterX + x, mapCenterY + y);
                    } else {
                        Tiles.drawTile(context, cell, mapCenterX + x, mapCenterY + y);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Cell emptyCell = map.getCell(0, 10);
                    Tiles.drawTile(context, emptyCell, mapCenterX + x, mapCenterY + y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        attackLabel.setText("" + map.getPlayer().getAttackStrength());
        defenseLabel.setText("" + map.getPlayer().getDefenseStrength());
        inventoryLabel.setText(map.getPlayer().getInventory().toString());
    }

}
