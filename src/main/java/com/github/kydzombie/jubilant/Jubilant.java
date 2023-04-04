package com.github.kydzombie.jubilant;

import com.github.kydzombie.jubilant.block.JubilantBlock;
import com.github.kydzombie.jubilant.item.Gauntlet;
import com.github.kydzombie.jubilant.item.JubilantItem;
import com.github.kydzombie.jubilant.item.Parchment;
import com.github.kydzombie.jubilant.item.Rune;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

public class Jubilant {
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    public static Rune BLANK_RUNE;
    public static Rune ENERGY_RUNE;
    public static Rune MATTER_RUNE;
    public static Rune INFO_RUNE;
    public static Rune VOID_RUNE;
    public static Rune TIME_RUNE;

    public static JubilantItem PARCHMENT;
    public static Parchment INSCRIBED_PARCHMENT;

    public static JubilantItem INERT_GAUNTLET;
    public static Gauntlet GAUNTLET;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        System.out.println(MOD_ID.getMetadata().getName() + " is registering blocks.");
        BLANK_RUNE = new Rune(MOD_ID.id("runeBlank"));
        ENERGY_RUNE = new Rune(MOD_ID.id("runeEnergy"));
        MATTER_RUNE = new Rune(MOD_ID.id("runeMatter"));
        INFO_RUNE = new Rune(MOD_ID.id("runeInfo"));
        VOID_RUNE = new Rune(MOD_ID.id("runeVoid"));
        TIME_RUNE = new Rune(MOD_ID.id("runeTime"));

        PARCHMENT = new JubilantItem(MOD_ID.id("parchment"), true);
        INSCRIBED_PARCHMENT = new Parchment(MOD_ID.id("parchmentInscribed"));

        INERT_GAUNTLET = new JubilantItem(MOD_ID.id("gauntletInert"), true);
        GAUNTLET = new Gauntlet(MOD_ID.id("gauntlet"));
    }

    public static JubilantBlock RUNIC_STONE;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        System.out.println(MOD_ID.getMetadata().getName() + " is registering blocks.");
        RUNIC_STONE = new JubilantBlock(MOD_ID.id("runicStone"), Material.STONE);
    }
}
