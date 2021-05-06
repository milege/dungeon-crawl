package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.logic.items.WarHammer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    Inventory inventory;
    Sword sword = new Sword();

    @BeforeEach
    public void init() {
        inventory = new Inventory();
        inventory.addToInventory(sword);
    }

    @Test
    void toStringNonEmptyInventoryReturnsItemNames() {
        String expected = "Inventory:\n" +
                "  Sword (weapon)\n";
        assertEquals(expected, inventory.toString());
    }

    @Test
    void toStringEmptyInventoryReturnsInventoryNameOnly() {
        inventory = new Inventory();
        String expected = "Inventory:\n";
        assertEquals(expected, inventory.toString());
    }

    @Test
    void isInInventoryNonexistentItemReturnsFalse() {
        assertFalse(inventory.isInInventory("cheese"));
    }

    @Test
    void isInInventoryExistingItemReturnsTrue() {
        assertTrue(inventory.isInInventory("sword"));
    }

    @Test
    void removeItemRemovedItemReturnsNull() {
        inventory.removeItem("Sword");
        assertNull(inventory.getItem("sword"));
    }

    @Test
    void getItemExistingItemReturnsItem() {
        Item expected = sword;
        assertEquals(expected, inventory.getItem("sword"));
    }

    @Test
    void getItemNonExistentItemReturnsNull() {
        assertNull(inventory.getItem("iphone"));
    }


    @Test
    void getItemsExistingItemReturnsItems() {
        WarHammer warHammer = new WarHammer();
        inventory.addToInventory(warHammer);
        ArrayList<Item> expected = new ArrayList<Item>();
        expected.add(sword);
        expected.add(warHammer);
        assertEquals(expected, inventory.getItems());
    }

    @Test
    void getItemsEmptyInventoryReturnsEmptyArrayList() {
        inventory = new Inventory();
        ArrayList<Item> expected = new ArrayList<Item>();
        assertEquals(expected, inventory.getItems());
    }

}