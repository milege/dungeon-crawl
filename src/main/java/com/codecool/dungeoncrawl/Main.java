package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    GameMap map = MapLoader.loadMap("/map.txt", CellType.FLOOR);
    GameMap firstMap;
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    TextField nameField = new TextField("Your name");
    Label nameLabel = new Label();
    Label healthLabel = new Label();
    Label attackLabel = new Label();
    Label defenseLabel = new Label();
    Label inventoryLabel = new Label();
    Button itemPickUpButton = new Button("Pick up item");
    Button drinkPotionButton = new Button("Drink Potion");
    Button nameSubmitButton = new Button("Submit");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        nameField.setPrefWidth(8);
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Enter name: "), 0, 0);
        ui.add(nameField, 0, 1);
        ui.add(nameSubmitButton,1,1);
        ui.add(new Label("Name: "), 0, 3);
        ui.add(nameLabel, 1, 3);
        ui.add(new Label("Health: "), 0, 4);
        ui.add(healthLabel, 1, 4);
        ui.add(new Label("Attack: "), 0, 5);
        ui.add(attackLabel, 1, 5);
        ui.add(new Label("Defense: "), 0, 6);
        ui.add(defenseLabel, 1, 6);
        ui.add(inventoryLabel, 0, 7);
        ui.add(itemPickUpButton,0,8);
        ui.add(drinkPotionButton,0,9);

        drinkPotionButton.setDisable(true);

        itemPickUpButton.setOnAction(onClick -> {
            map.getPlayer().pickUpItem();
            if (map.getPlayer().getInventory().isInInventory("Potion")){
                enablePotionButton();
            }
            ui.requestFocus();
            refresh();
        });

        nameSubmitButton.setOnAction(onClick -> {
            map.getPlayer().setName(nameField.getText());
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
        if (map.getPlayer().getCell().getTileName().equals("stairs")) loadNewMap();
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int playerCellX = map.getPlayer().getCell().getX();
        int playerCellY = map.getPlayer().getCell().getY();
        int screenCenterX = 12;
        int screenCenterY = 10;
        int visionRadius = map.getPlayer().getVision();

        for (int x = -visionRadius ; x <= visionRadius; x++) {
            for (int y = -visionRadius; y <= visionRadius; y++) {
                try {
                    Cell cell = map.getCell(playerCellX + x, playerCellY + y);
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), screenCenterX + x, screenCenterY + y);
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), screenCenterX + x, screenCenterY + y);
                    } else {
                        Tiles.drawTile(context, cell, screenCenterX + x, screenCenterY + y);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Cell emptyCell = map.getCell(0, 10);
                    Tiles.drawTile(context, emptyCell, screenCenterX + x, screenCenterY + y);
                }
            }
        }
        nameLabel.setText("" + nameField.getText());
        healthLabel.setText("" + map.getPlayer().getHealth());
        attackLabel.setText("" + map.getPlayer().getAttackStrength());
        defenseLabel.setText("" + map.getPlayer().getDefenseStrength());
        inventoryLabel.setText(map.getPlayer().getInventory().toString());
    }

    private void loadNewMap() {
        firstMap = map;
        map = MapLoader.loadMap("/map2.txt", CellType.GRASS);
        refresh();
    }

    private void enablePotionButton(){

    }
}
