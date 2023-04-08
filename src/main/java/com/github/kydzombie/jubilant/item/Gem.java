package com.github.kydzombie.jubilant.item;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.client.color.item.ItemColorProvider;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.HexFormat;
import java.util.Random;

public class Gem extends JubilantItem implements ItemColorProvider {
    private static final int MAX_HEX = HexFormat.fromHexDigits("FFFFFF");
    public Gem(Identifier identifier) {
        super(identifier, true);
        setMaxStackSize(1);
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        var nbt = itemInstance.getStationNBT();
        var rand = new Random();
        nbt.put("colorTop", rand.nextInt(0, MAX_HEX));
        nbt.put("colorMiddle", rand.nextInt(0, MAX_HEX));
        nbt.put("colorBottom", rand.nextInt(0, MAX_HEX));
        player.swingHand();
        return super.use(itemInstance, level, player);
    }

    @Override
    public int getColor(ItemInstance itemInstance, int layer) {
        return switch(layer) {
            case 0 -> itemInstance.getStationNBT().getInt("colorTop");
            case 1 -> itemInstance.getStationNBT().getInt("colorMiddle");
            case 2 -> itemInstance.getStationNBT().getInt("colorBottom");
            default -> MAX_HEX;
        };
    }
}
