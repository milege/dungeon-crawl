package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Shield shield;
    Weapon weapon;
    Key key;
    Health health;
    Torch torch;

    @BeforeEach
    public void init() {
        shield = new SmallShield();
        weapon = new Sword();
        key = new Key();
        health = new Potion();
        torch = new Torch();
    }

    @Test
    void getTileNameReturnsTileName() {
        assertEquals("small shield", shield.getTileName());
        assertEquals("sword", weapon.getTileName());
        assertEquals("key", key.getTileName());
        assertEquals("potion", health.getTileName());
        assertEquals("torch", torch.getTileName());
    }

    @Test
    void getNameReturnsName() {
        assertEquals("Small shield", shield.getName());
        assertEquals("Sword", weapon.getName());
        assertEquals("Key", key.getName());
        assertEquals("Potion", health.getName());
        assertEquals("Torch", torch.getName());
    }

    @Test
    void getTypeReturnsType() {
        assertEquals("shield", shield.getType());
        assertEquals("weapon", weapon.getType());
        assertEquals("key", key.getType());
        assertEquals("health", health.getType());
        assertEquals("vision", torch.getType());
    }

    @Test
    void torchGetExtraVisionReturnsExtraVision() {
        int expected = 6;
        assertEquals(expected, torch.getExtraVision());
    }

    @Test
    void weaponGetAdditionalAttackStrengthReturnsAttackStrength() {
        int expected = 10;
        assertEquals(expected, weapon.getAdditionalAttackStrength());
    }

    @Test
    void weaponSetAdditionalAttackStrengthSetsAttackStrength() {
        weapon.setAdditionalAttackStrength(12);
        int expected = 12;
        assertEquals(expected, weapon.getAdditionalAttackStrength());
    }

    @Test
    void shieldGetAdditionalDefenseStrengthReturnsDefenseStrength() {
        int expected = 2;
        assertEquals(expected, shield.getAdditionalDefenseStrength());
    }

    @Test
    void shieldSetAdditionalDefenseStrengthSetsDefenseStrength() {
        shield.setAdditionalDefenseStrength(12);
        int expected = 12;
        assertEquals(expected, shield.getAdditionalDefenseStrength());
    }

    @Test
    void healthGetAdditionalHealthReturnsAdditionalHealth() {
        int expected = 5;
        assertEquals(expected, health.getAdditionalHealth());
    }

    @Test
    void healthSetAdditionalHealthSetsAdditionalHealth() {
        health.setAdditionalHealth(12);
        int expected = 12;
        assertEquals(expected, health.getAdditionalHealth());
    }

}