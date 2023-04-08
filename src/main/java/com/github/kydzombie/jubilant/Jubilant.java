package com.github.kydzombie.jubilant;

import com.github.kydzombie.jubilant.block.JubilantBlock;
import com.github.kydzombie.jubilant.block.SpellTable;
import com.github.kydzombie.jubilant.item.*;
import com.github.kydzombie.jubilant.spell.FireSpell;
import com.github.kydzombie.jubilant.spell.SpellRegistry;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.client.event.color.item.ItemColorsRegisterEvent;
import net.modificationstation.stationapi.api.client.event.render.model.ItemModelPredicateProviderRegistryEvent;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.registry.ItemModelPredicateProviderRegistry;
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

    public static Gem SPELL_GEM;

    public static JubilantItem PARCHMENT;
    public static Parchment INSCRIBED_PARCHMENT;

    public static JubilantItem INERT_GAUNTLET;
    public static Gauntlet GAUNTLET;

    public static Satchel SATCHEL;

    public static JubilantBook SPELL_JOURNAL;
    public static JubilantBook ENCHANTOPEDIA;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        System.out.println(MOD_ID.getMetadata().getName() + " is registering blocks.");
        BLANK_RUNE = new Rune(MOD_ID.id("runeBlank"));
        ENERGY_RUNE = new Rune(MOD_ID.id("runeEnergy"));
        MATTER_RUNE = new Rune(MOD_ID.id("runeMatter"));
        INFO_RUNE = new Rune(MOD_ID.id("runeInfo"));
        VOID_RUNE = new Rune(MOD_ID.id("runeVoid"));
        TIME_RUNE = new Rune(MOD_ID.id("runeTime"));

        SPELL_GEM = new Gem(MOD_ID.id("spellGem"));

        PARCHMENT = new JubilantItem(MOD_ID.id("parchment"), true);
        INSCRIBED_PARCHMENT = new Parchment(MOD_ID.id("parchmentInscribed"));

        INERT_GAUNTLET = new JubilantItem(MOD_ID.id("gauntletInert"), true);
        GAUNTLET = new Gauntlet(MOD_ID.id("gauntlet"));

        SATCHEL = new Satchel(MOD_ID.id("satchel"));

        SPELL_JOURNAL = new JubilantBook(MOD_ID.id("spellJournal"));
        ENCHANTOPEDIA = new JubilantBook(MOD_ID.id("enchantopedia"));
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
    }

    @EventListener
    public void registerItemModelPredicates(ItemModelPredicateProviderRegistryEvent event) {
        ItemModelPredicateProviderRegistry.INSTANCE.register(SATCHEL, MOD_ID.id("open"),
                (itemInstance, world, entity, seed) ->
                        Satchel.isOpen(itemInstance)? 1 : 0);
    }

    @EventListener
    public void registerItemColorProviders(ItemColorsRegisterEvent event) {
        event.itemColors.register(SPELL_GEM, SPELL_GEM);
        event.itemColors.register(GAUNTLET, GAUNTLET);
    }

    @EventListener
    public void init(InitEvent event) {
        registerSpells();
    }

    public void registerSpells() {
        SpellRegistry.registerSpell("fire", new FireSpell(10));
    }
}
