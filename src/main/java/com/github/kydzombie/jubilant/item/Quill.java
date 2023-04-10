package com.github.kydzombie.jubilant.item;

import net.modificationstation.stationapi.api.registry.Identifier;

public class Quill extends JubilantItem {
    public Quill(Identifier identifier, int durability) {
        super(identifier, true);
        setMaxStackSize(1);
        setDurability(durability);
    }
}
