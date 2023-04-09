package com.github.kydzombie.jubilant.block;

import com.github.kydzombie.jubilant.block.entity.VanishingBlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntityBase;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;

public class VanishingBlock extends TemplateBlockWithEntity {
    public VanishingBlock(Identifier identifier) {
        super(identifier, Material.STONE);
    }

    @Override
    protected TileEntityBase createTileEntity() {
        return new VanishingBlockEntity(20);
    }

}
