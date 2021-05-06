package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.model.*;
import com.google.gson.Gson;
import org.json.simple.JSONObject;

public class SerializeHandler {

    public JSONObject serializeSaveState(GameMap map, String currentMap){
        PlayerModel playerModel = new PlayerModel(map.getPlayer());
        InventoryModel inventoryModel = new InventoryModel(map.getPlayer().getInventory());
        MonstersModel monstersModel = new MonstersModel(map);
        ItemsModel itemsModel = new ItemsModel(map);
        SerializationModel serializationModel = new SerializationModel(currentMap, playerModel, inventoryModel, monstersModel, itemsModel);
        String serialized = new Gson().toJson(serializationModel);
        JSONObject serializedObj = new JSONObject();
        serializedObj.put("SaveState", serialized);
        return serializedObj;
    }
}
