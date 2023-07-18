package com.github.kydzombie.jubilant.item.gem;

import com.github.kydzombie.jubilant.item.JubilantItem;
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
        super(identifier, false);
        setMaxStackSize(1);
    }

    @Override
    public int getColor(ItemInstance itemInstance, int layer) {
        if (itemInstance == null || !(itemInstance.getType() instanceof Gem)) return MAX_HEX;
        return switch(layer) {
            case 0 -> itemInstance.getStationNBT().getInt("colorTop");
            case 1 -> itemInstance.getStationNBT().getInt("colorMiddle");
            case 2 -> itemInstance.getStationNBT().getInt("colorBottom");
            default -> MAX_HEX;
        };
    }
}
