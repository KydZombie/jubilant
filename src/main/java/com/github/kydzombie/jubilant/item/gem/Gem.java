package com.github.kydzombie.jubilant.item.gem;

import com.github.kydzombie.jubilant.item.JubilantItem;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.HexFormat;

public class Gem extends JubilantItem {
    private static final int MAX_HEX = HexFormat.fromHexDigits("FFFFFF");
    public Gem(Identifier identifier) {
        super(identifier, false);
        setMaxStackSize(1);
    }
}
