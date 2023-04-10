package com.github.kydzombie.jubilant.block;

import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Box;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.Random;

public class LightBlock extends JubilantBlock {
    public LightBlock(Identifier identifier) {
        super(identifier, Material.GLASS);
        setLightEmittance(1);
    }

    @Override
    public boolean isFullOpaque() {
        return false;
    }

    @Override
    public int getDropId(int i, Random random) {
        return 0;
    }

    @Override
    public Box getCollisionShape(Level level, int x, int y, int z) {
        return null;
    }

    @Override
    public Box getOutlineShape(Level level, int x, int y, int z) {
        return Box.create(x + .25, y + .25, z + .25, x + .75, y + .75, z + .75);
    }

}
