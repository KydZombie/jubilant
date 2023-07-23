package com.github.kydzombie.jubilant.block;

import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.EnumProperty;
import net.modificationstation.stationapi.api.util.math.Direction;

public class SpellTable extends JubilantBlock {
    public static final EnumProperty<Direction> FACING_PROPERTY = EnumProperty.of("facing", Direction.class);

    public SpellTable(Identifier identifier) {
        super(identifier, Material.WOOD);
        setDefaultState(getStateManager().getDefaultState().with(FACING_PROPERTY, Direction.NORTH));
    }

    @Override
    public void appendProperties(StateManager.Builder<BlockBase, BlockState> builder) {
        builder.add(FACING_PROPERTY);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(FACING_PROPERTY,
                context.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public boolean isFullOpaque() {
        return false;
    }
}
