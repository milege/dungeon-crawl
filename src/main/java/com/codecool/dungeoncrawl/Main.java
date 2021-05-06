package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.model.*;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;


public class Main extends Application {
    GameDatabaseManager gameDatabaseManager = new GameDatabaseManager();
    SerializeHandler serializeHandler = new SerializeHandler();
    GameMap map = MapLoader.loadMap("/map.txt", CellType.FLOOR);
    String currentMap = "/map.txt";
    GameMap oldMap;
    Canvas canvas = new Canvas(
            25 * Tiles.TILE_WIDTH,
            25 * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    TextField nameField = new TextField("Player");
    Label nameLabel = new Label();
    Label healthLabel = new Label();
    Label attackLabel = new Label();
    Label defenseLabel = new Label();
    Label inventoryLabel = new Label();
    Button itemPickUpButton = new Button("Pick up item");
    Button drinkPotionButton = new Button("Drink Potion");
    Button nameSubmitButton = new Button("Submit");
    Button saveGameButton = new Button("Save Game");
    Button modalButton = new Button("Load Game");
    Label loadGameInfoLabel = new Label("Click a number to load gamesave!");
    Image logo = new Image("/logo.png", 180, 100, true, false);
    MenuItem menuExport = new MenuItem("Export");
    MenuItem menuImport = new MenuItem("Import");
    MenuItem menuExit = new MenuItem("Cancel");
    MenuButton menuButton = new MenuButton("File", null, menuExport, menuImport, menuExit);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gameDatabaseManager.setup();
        nameField.setPrefWidth(8);
        GridPane ui = new GridPane();
        ui.setPrefWidth(300);
        ui.setPadding(new Insets(10));
        ui.setVgap(5);
        ui.setStyle("-fx-background-color:#ad9d94; -fx-font-size: 16");

        ui.add(new ImageView(logo), 0, 0);
        ui.add(new Label("Enter name: "), 0, 1);
        ui.add(nameField, 0, 2);
        ui.add(nameSubmitButton,1,2);
        ui.add(new Label("Name: "), 0, 4);
        ui.add(nameLabel, 1, 4);
        ui.add(new Label("Health: "), 0, 5);
        ui.add(healthLabel, 1, 5);
        ui.add(new Label("Attack: "), 0, 6);
        ui.add(attackLabel, 1, 6);
        ui.add(new Label("Defense: "), 0, 7);
        ui.add(defenseLabel, 1, 7);
        ui.add(inventoryLabel, 0, 8);
        ui.add(itemPickUpButton,0,9);
        ui.add(drinkPotionButton,0,10);
        ui.add(saveGameButton, 0,11);
        ui.add(modalButton, 0,12);
        ui.add(menuButton, 0, 13);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        drinkPotionButton.setDisable(true);

        itemPickUpButton.setOnAction(onClick -> {
            map.getPlayer().pickUpItem();
            if (map.getPlayer().getInventory().isInInventory("Potion")){
                drinkPotionButton.setDisable(false);
            }
            ui.requestFocus();
            refresh();
        });

        drinkPotionButton.setOnAction(onClick -> {
            map.getPlayer().getInventory().getItem("Potion").startAction(map.getPlayer());
            map.getPlayer().getInventory().removeItem("Potion");
            drinkPotionButton.setDisable(true);
            ui.requestFocus();
            refresh();
        });

        nameSubmitButton.setOnAction(onClick -> {
            map.getPlayer().setName(nameField.getText());
            ui.requestFocus();
            refresh();
        });

        saveGameButton.setOnAction(onClick -> {
            if (gameDatabaseManager.saveExist(map.getPlayer().getName())){
                Alert alert = new Alert(AlertType.NONE, "Do you want to overwrite your previous save?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    gameDatabaseManager.updateSave(map.getPlayer(), currentMap, map);
                }
            }else {
                gameDatabaseManager.saveGame(map.getPlayer(), currentMap, map);
            }
            ui.requestFocus();
            refresh();
        });

        modalButton.setOnAction(onClick -> {
            GridPane modalUi = new GridPane();
            modalUi.setPrefWidth(600);
            modalUi.setPadding(new Insets(10));
            modalUi.setVgap(5);
            modalUi.setStyle("-fx-background-color:#ad9d94; -fx-font-size: 16");
            modalUi.add(loadGameInfoLabel, 0, 0);
            loadGameInfoLabel.setStyle("-fx-font-weight: bold");
            StringBuilder text = new StringBuilder();
            int i = 1;
            i = listSavedGames(modalUi, text, i);
            VBox vboxForButtons = new VBox();
            placeLoadButtons(modalUi, i, vboxForButtons);
            Scene modalScene = new Scene(modalUi);
            Stage stage = new Stage();
            stage.setTitle("LOAD GAME");
            stage.setScene(modalScene);
            stage.initModality(Modality.NONE);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            ui.requestFocus();
            refresh();
        });

        menuExit.setOnAction(onClick ->{
            ui.requestFocus();
            refresh();
        });

        menuExport.setOnAction(onClick -> {
            File saveFile = fileChooser.showSaveDialog(primaryStage);
            if (saveFile != null) {
                JSONObject serializedObj = serializeHandler.serializeSaveState(map, currentMap);
                try (FileWriter file = new FileWriter(saveFile.getAbsolutePath())) {
                    file.write(serializedObj.toJSONString());
                    file.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ui.requestFocus();
            refresh();
        });

        menuImport.setOnAction(OnClick -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                JSONParser parser = new JSONParser();
                try {
                    Object obj = parser.parse(new FileReader(selectedFile));
                    SerializationModel saveModel = serializeHandler.deserializeSaveState((JSONObject) obj);
                    map = serializeHandler.loadSaveState(saveModel, map.getPlayer());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ui.requestFocus();
            refresh();
        });

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        ui.requestFocus();
    }

    private void placeLoadButtons(GridPane modalUi, int i, VBox vboxForButtons) {
        for (int j = 1; j < i; j++){
            Button btnNumber = new Button();
            btnNumber.setPrefWidth(220);
            btnNumber.setText(String.valueOf(j));
            int playerId = j;
            btnNumber.setOnAction((ActionEvent)->{
                oldMap = map;
                GameState loadedGameState = gameDatabaseManager.getGameState(playerId);
                map = StateLoader.loadMapState(loadedGameState.getCurrentMap());
                StateLoader.loadPlayerState(oldMap.getPlayer(), gameDatabaseManager.getPlayer(playerId),
                        map, gameDatabaseManager.getInventory(playerId));
                StateLoader.loadMapElements(gameDatabaseManager.getItems(loadedGameState.getId()),
                        gameDatabaseManager.getMonsters(loadedGameState.getId()), map);
                refresh();
            });
            vboxForButtons.getChildren().add(btnNumber);
            modalUi.add(vboxForButtons, 0,j);
            vboxForButtons = new VBox();
        }
    }

    private int listSavedGames(GridPane modalUi, StringBuilder text, int i) {
        for (PlayerModel model : gameDatabaseManager.getAll())
        {
            text.append("  (Player name: ")
                    .append(model.getPlayerName())
                    .append(") ")
                    .append("\n");
            modalUi.add(new Label(text.toString()), 1, i);
            text = new StringBuilder();
            i++;
        }
        return i;
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
        int screenCenterY = 12;
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
        nameLabel.setText("" + map.getPlayer().getName());
        healthLabel.setText("" + map.getPlayer().getHealth());
        attackLabel.setText("" + map.getPlayer().getAttackStrength());
        defenseLabel.setText("" + map.getPlayer().getDefenseStrength());
        inventoryLabel.setText(map.getPlayer().getInventory().toString());
    }

    private void loadNewMap() {
        map = MapLoader.loadMap("/map2.txt", CellType.GRASS);
        currentMap = "/map2.txt";
        refresh();
    }
}
