package com.github.kydzombie.jubilant.block.entity;

import net.minecraft.tileentity.TileEntityBase;

public class VanishingBlockEntity extends TileEntityBase {
    private int ticksLeft;
    public VanishingBlockEntity(int ticks) {
        this.ticksLeft = ticks;
    }

    @Override
    public void tick() {
        if (ticksLeft <= 0) {
            level.setTile(x, y, z, 0);
        } else {
            ticksLeft--;
        }
    }
}
