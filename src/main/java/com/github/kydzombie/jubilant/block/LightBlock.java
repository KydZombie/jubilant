package com.github.kydzombie.jubilant.block;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.registry.Identifier;

public class LightBlock extends JubilantBlock {
    public LightBlock(Identifier identifier) {
        super(identifier, Material.GLASS);
        setLightEmittance(1);
    }
}
