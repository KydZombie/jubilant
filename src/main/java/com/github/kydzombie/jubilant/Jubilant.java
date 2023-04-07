package com.github.kydzombie.jubilant;

import com.github.kydzombie.jubilant.block.JubilantBlock;
import com.github.kydzombie.jubilant.block.SpellTable;
import com.github.kydzombie.jubilant.item.*;
import com.github.kydzombie.jubilant.spell.FireSpell;
import com.github.kydzombie.jubilant.spell.SpellRegistry;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
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

    public static Gem RED_GEM;
    public static Gem GREEN_GEM;
    public static Gem PURPLE_GEM;

    public static JubilantItem PARCHMENT;
    public static Parchment INSCRIBED_PARCHMENT;

    public static JubilantItem INERT_GAUNTLET;
    public static Gauntlet GAUNTLET;

    public static Satchel SATCHEL;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        System.out.println(MOD_ID.getMetadata().getName() + " is registering blocks.");
        BLANK_RUNE = new Rune(MOD_ID.id("runeBlank"));
        ENERGY_RUNE = new Rune(MOD_ID.id("runeEnergy"));
        MATTER_RUNE = new Rune(MOD_ID.id("runeMatter"));
        INFO_RUNE = new Rune(MOD_ID.id("runeInfo"));
        VOID_RUNE = new Rune(MOD_ID.id("runeVoid"));
        TIME_RUNE = new Rune(MOD_ID.id("runeTime"));

        RED_GEM = new Gem(MOD_ID.id("gemRed"));
        GREEN_GEM = new Gem(MOD_ID.id("gemGreen"));
        PURPLE_GEM = new Gem(MOD_ID.id("gemPurple"));

        PARCHMENT = new JubilantItem(MOD_ID.id("parchment"), true);
        INSCRIBED_PARCHMENT = new Parchment(MOD_ID.id("parchmentInscribed"));

        INERT_GAUNTLET = new JubilantItem(MOD_ID.id("gauntletInert"), true);
        GAUNTLET = new Gauntlet(MOD_ID.id("gauntlet"));

        SATCHEL = new Satchel(MOD_ID.id("satchel"));
    }

    public static JubilantBlock RUNIC_STONE;
    public static SpellTable SPELL_TABLE;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        System.out.println(MOD_ID.getMetadata().getName() + " is registering blocks.");
        RUNIC_STONE = new JubilantBlock(MOD_ID.id("runicStone"), Material.STONE);
        SPELL_TABLE = new SpellTable(MOD_ID.id("spellTable"));
    }

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        System.out.println(MOD_ID.getMetadata().getName() + " is registering textures.");
        RUNIC_STONE.texture = Atlases.getTerrain().addTexture(MOD_ID.id("blocks/runicStone")).index;

        RED_GEM.setTexture(MOD_ID.id("items/gemRed"));
        GREEN_GEM.setTexture(MOD_ID.id("items/gemGreen"));
        PURPLE_GEM.setTexture(MOD_ID.id("items/gemPurple"));
    }

    @EventListener
    public void init(InitEvent event) {
        registerSpells();
    }

    public void registerSpells() {
        SpellRegistry.registerSpell("fire", new FireSpell(10));
    }
}
