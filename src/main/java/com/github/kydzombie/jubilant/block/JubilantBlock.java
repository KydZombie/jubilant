package com.github.kydzombie.jubilant.block;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

public class JubilantBlock extends TemplateBlockBase {
    public JubilantBlock(Identifier identifier, Material material) {
        super(identifier, material);
        setTranslationKey(identifier.toString());
    }
}
