package com.github.kydzombie.jubilant.item;

import net.modificationstation.stationapi.api.registry.Identifier;

public class Gauntlet extends JubilantItem {
    public Gauntlet(Identifier identifier) {
        super(identifier, false);
        setMaxStackSize(1);
    }
}
