package com.github.kydzombie.jubilant.item.gem;

import com.github.kydzombie.jubilant.item.JubilantItem;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.client.color.item.ItemColorProvider;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.HexFormat;
import java.util.Random;

public class Gem extends JubilantItem {
    private static final int MAX_HEX = HexFormat.fromHexDigits("FFFFFF");
    public Gem(Identifier identifier) {
        super(identifier, false);
        setMaxStackSize(1);
    }
}
