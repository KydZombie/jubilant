package com.github.kydzombie.jubilant;

import com.github.kydzombie.extendedinventory.ExtendedInventoryUtil;
import com.github.kydzombie.jubilant.inventory.InventoryDave;
import com.github.kydzombie.jubilant.item.Satchel;
import com.github.kydzombie.jubilant.item.gem.Gem;
import com.github.kydzombie.jubilant.item.gem.SpellGem;
import com.github.kydzombie.jubilant.spell.SpellRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.modificationstation.stationapi.api.client.event.color.item.ItemColorsRegisterEvent;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import net.modificationstation.stationapi.api.client.event.render.model.ItemModelPredicateProviderRegistryEvent;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.registry.ItemModelPredicateProviderRegistry;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.packet.PacketHelper;
import org.lwjgl.input.Keyboard;

import java.util.HexFormat;

public class JubilantClient {
    public static KeyBinding randomizeGem;

    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        var list = event.keyBindings;
        list.add(randomizeGem = new KeyBinding("key.jubilant.randomizeGem", Keyboard.KEY_R));
    }

    @EventListener
    public void keyPressed(KeyStateChangedEvent event) {
        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(randomizeGem.key)) {
            PacketHelper.send(new Message(Jubilant.MOD_ID.id("randomizeGem")));
        }
    }

    @EventListener
    public void registerItemColorProviders(ItemColorsRegisterEvent event) {
        for (var gem :
                new ItemBase[] {
                        Jubilant.SPELL_GEM,
                        Jubilant.UPGRADE_GEM,
                        Jubilant.BUFF_GEM
                }) {
            event.itemColors.register((itemInstance, layer) -> {
                return switch(layer) {
                    case 0 -> itemInstance.getStationNBT().getInt("colorTop");
                    case 1 -> itemInstance.getStationNBT().getInt("colorMiddle");
                    case 2 -> itemInstance.getStationNBT().getInt("colorBottom");
                    default -> Jubilant.MAX_HEX;
                };
            }, gem);
        }
    }

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        System.out.println(Jubilant.MOD_ID.getMetadata().getName() + " is registering textures.");
        Jubilant.RUNIC_STONE.texture = Atlases.getTerrain().addTexture(Jubilant.MOD_ID.id("blocks/runicStone")).index;
    }

    @EventListener
    public void registerItemModelPredicates(ItemModelPredicateProviderRegistryEvent event) {
        ItemModelPredicateProviderRegistry.INSTANCE.register(Jubilant.SATCHEL, Jubilant.MOD_ID.id("open"),
                (itemInstance, world, entity, seed) ->
                        Satchel.isOpen(itemInstance) ? 1 : 0);

        ItemModelPredicateProviderRegistry.INSTANCE.register(Jubilant.DAVE, Jubilant.MOD_ID.id("pages"),
                (itemInstance, world, entity, seed) -> {
                    InventoryDave daveInventory = new InventoryDave(itemInstance);
                    var parchment = daveInventory.getInventoryItem(InventoryDave.PARCHMENT_SLOT);
                    return parchment != null ? parchment.count / (float) parchment.getMaxStackSize() : 0;
                });


        ItemModelPredicateProviderRegistry.INSTANCE.register(Jubilant.DAVE, Jubilant.MOD_ID.id("quill"),
                (itemInstance, world, entity, seed) -> {
                    InventoryDave daveInventory = new InventoryDave(itemInstance);
                    var quill = daveInventory.getInventoryItem(InventoryDave.QUILL_SLOT);
                    return quill != null ? 1 : 0;
                });

        ItemModelPredicateProviderRegistry.INSTANCE.register(Jubilant.SPELL_GEM, Jubilant.MOD_ID.id("has_gauntlet"),
                (itemInstance, world, entity, seed) -> {
                    if (entity instanceof PlayerBase player) {
                        if (ExtendedInventoryUtil.getTrinketHandler(player).hasTrinket(Jubilant.GAUNTLET)) {
                            return 1;
                        }
                    }
                    return 0;
                });
    }
}
