package com.github.kydzombie.jubilant;

import com.github.kydzombie.extendedinventory.ExtendedInventoryUtil;
import com.github.kydzombie.jubilant.block.JubilantBlock;
import com.github.kydzombie.jubilant.block.LightBlock;
import com.github.kydzombie.jubilant.block.SpellTable;
import com.github.kydzombie.jubilant.block.VanishingBlock;
import com.github.kydzombie.jubilant.block.entity.VanishingBlockEntity;
import com.github.kydzombie.jubilant.container.ContainerGauntlet;
import com.github.kydzombie.jubilant.inventory.InventoryDave;
import com.github.kydzombie.jubilant.inventory.InventoryGauntlet;
import com.github.kydzombie.jubilant.item.*;
import com.github.kydzombie.jubilant.item.gem.BuffGem;
import com.github.kydzombie.jubilant.item.gem.Gem;
import com.github.kydzombie.jubilant.item.gem.SpellGem;
import com.github.kydzombie.jubilant.item.gem.UpgradeGem;
import com.github.kydzombie.jubilant.spell.*;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.client.event.color.item.ItemColorsRegisterEvent;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import net.modificationstation.stationapi.api.client.event.render.model.ItemModelPredicateProviderRegistryEvent;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.registry.ItemModelPredicateProviderRegistry;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.tileentity.TileEntityRegisterEvent;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import org.lwjgl.input.Keyboard;

import java.util.HexFormat;
import java.util.Random;

public class Jubilant {
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    public static Rune BLANK_RUNE;
    public static Rune ENERGY_RUNE;
    public static Rune MATTER_RUNE;
    public static Rune INFO_RUNE;
    public static Rune VOID_RUNE;
    public static Rune TIME_RUNE;

    public static SpellGem SPELL_GEM;
    public static UpgradeGem UPGRADE_GEM;
    public static BuffGem BUFF_GEM;


    public static JubilantItem PARCHMENT;
    public static Parchment INSCRIBED_PARCHMENT;

    public static Quill ENCHANTED_QUILL;

    public static JubilantItem INERT_GAUNTLET;
    public static Gauntlet GAUNTLET;

    public static Satchel SATCHEL;

    public static JubilantBook SPELL_JOURNAL;
    public static JubilantBook ENCHANTOPEDIA;

    public static Dave DAVE;
    public static JubilantBook DENNIS;
    public static JubilantBook DUSTIN;
    public static JubilantBook DERRICK;


    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        System.out.println(MOD_ID.getMetadata().getName() + " is registering blocks.");
        BLANK_RUNE = new Rune(MOD_ID.id("runeBlank"));
        ENERGY_RUNE = new Rune(MOD_ID.id("runeEnergy"));
        MATTER_RUNE = new Rune(MOD_ID.id("runeMatter"));
        INFO_RUNE = new Rune(MOD_ID.id("runeInfo"));
        VOID_RUNE = new Rune(MOD_ID.id("runeVoid"));
        TIME_RUNE = new Rune(MOD_ID.id("runeTime"));

        SPELL_GEM = new SpellGem(MOD_ID.id("spellGem"));
        UPGRADE_GEM = new UpgradeGem(MOD_ID.id("upgradeGem"));
        BUFF_GEM = new BuffGem(MOD_ID.id("buffGem"));

        PARCHMENT = new JubilantItem(MOD_ID.id("parchment"), true);
        INSCRIBED_PARCHMENT = new Parchment(MOD_ID.id("parchmentInscribed"));

        ENCHANTED_QUILL = new Quill(MOD_ID.id("enchantedQuill"), 16);

        INERT_GAUNTLET = new JubilantItem(MOD_ID.id("gauntletInert"), true);
        GAUNTLET = new Gauntlet(MOD_ID.id("gauntlet"));

        SATCHEL = new Satchel(MOD_ID.id("satchel"));

        SPELL_JOURNAL = new JubilantBook(MOD_ID.id("spellJournal"));
        ENCHANTOPEDIA = new JubilantBook(MOD_ID.id("enchantopedia"));
        DAVE = new Dave(MOD_ID.id("dave"));
    }

    public static JubilantBlock RUNIC_STONE;
    public static SpellTable SPELL_TABLE;
    public static VanishingBlock VANISHING_BLOCK;
    public static LightBlock LIGHT_BLOCK;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        System.out.println(MOD_ID.getMetadata().getName() + " is registering blocks.");
        RUNIC_STONE = new JubilantBlock(MOD_ID.id("runicStone"), Material.STONE);
        SPELL_TABLE = new SpellTable(MOD_ID.id("spellTable"));
        VANISHING_BLOCK = new VanishingBlock(MOD_ID.id("vanishingBlock"));
        LIGHT_BLOCK = new LightBlock(MOD_ID.id("lightBlock"));
    }

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        System.out.println(MOD_ID.getMetadata().getName() + " is registering textures.");
        RUNIC_STONE.texture = Atlases.getTerrain().addTexture(MOD_ID.id("blocks/runicStone")).index;
    }

    @EventListener
    public void registerTileEntities(TileEntityRegisterEvent event) {
        event.register(VanishingBlockEntity.class, "jubilant:vanishingBlock");
    }

    @EventListener
    public void registerItemModelPredicates(ItemModelPredicateProviderRegistryEvent event) {
        ItemModelPredicateProviderRegistry.INSTANCE.register(SATCHEL, MOD_ID.id("open"),
                (itemInstance, world, entity, seed) ->
                        Satchel.isOpen(itemInstance) ? 1 : 0);

        ItemModelPredicateProviderRegistry.INSTANCE.register(DAVE, MOD_ID.id("pages"),
                (itemInstance, world, entity, seed) -> {
                    InventoryDave daveInventory = new InventoryDave(itemInstance);
                    var parchment = daveInventory.getInventoryItem(InventoryDave.PARCHMENT_SLOT);
                    return parchment != null ? parchment.count / (float) parchment.getMaxStackSize() : 0;
                });


        ItemModelPredicateProviderRegistry.INSTANCE.register(DAVE, MOD_ID.id("quill"),
                (itemInstance, world, entity, seed) -> {
                    InventoryDave daveInventory = new InventoryDave(itemInstance);
                    var quill = daveInventory.getInventoryItem(InventoryDave.QUILL_SLOT);
                    return quill != null ? 1 : 0;
                });

        ItemModelPredicateProviderRegistry.INSTANCE.register(SPELL_GEM, MOD_ID.id("has_gauntlet"),
                (itemInstance, world, entity, seed) -> {
                    if (entity instanceof PlayerBase player) {
                        if (ExtendedInventoryUtil.getTrinketHandler(player).hasTrinket(GAUNTLET)) {
                            return 1;
                        }
                    }
                    return 0;
                });
    }

    @EventListener
    public void registerItemColorProviders(ItemColorsRegisterEvent event) {
        event.itemColors.register(SPELL_GEM, SPELL_GEM);
        event.itemColors.register(UPGRADE_GEM, UPGRADE_GEM);
        event.itemColors.register(BUFF_GEM, BUFF_GEM);
    }

    public static KeyBinding nextSpell;

    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        var list = event.keyBindings;
        list.add(nextSpell = new KeyBinding("key.jubilant.nextSpell", Keyboard.KEY_R));
    }

    private static final int MAX_HEX = HexFormat.fromHexDigits("FFFFFF");

    @EventListener
    public void keyPressed(KeyStateChangedEvent event) {
        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(nextSpell.key)) {
            var player = ((Minecraft) FabricLoader.getInstance().getGameInstance()).player;
            var item = player.getHeldItem();
            if (item == null) return;
            if (item.getType() instanceof Gem) {
                var nbt = item.getStationNBT();
                var rand = new Random();
                nbt.put("colorTop", rand.nextInt(0, MAX_HEX));
                nbt.put("colorMiddle", rand.nextInt(0, MAX_HEX));
                nbt.put("colorBottom", rand.nextInt(0, MAX_HEX));
                player.swingHand();

                if (item.getType() instanceof SpellGem) {
                    item.getStationNBT().put("spell", SpellRegistry.getRandomSpellName());
                }
            }
        }
    }

    @EventListener
    public void init(InitEvent event) {
        registerSpells();
    }

    public void registerSpells() {
        SpellRegistry.registerSpell(new FireSpell(10));
        SpellRegistry.registerSpell(new MineSpell(10));
        SpellRegistry.registerSpell(new DamageSpell(10, 10));
        SpellRegistry.registerSpell(new LightningSpell(10));
        SpellRegistry.registerSpell(new VanishingBlockSpell(10));
        SpellRegistry.registerSpell(new LightBlockSpell(10));
    }
}
