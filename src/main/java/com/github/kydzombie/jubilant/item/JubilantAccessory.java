package com.github.kydzombie.jubilant.item;

import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.ArrayList;
import java.util.List;

public class JubilantAccessory extends JubilantItem implements Accessory {
    private final String[] types;

    public JubilantAccessory(Identifier identifier, boolean createDescription, String... types) {
        super(identifier, createDescription);
        this.types = types;
    }

    @Override
    public String[] getAccessoryTypes(ItemInstance item) {
        return types;
    }

    @Override
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        var original = super.getTooltip(itemInstance, originalTooltip);
        if (types.length < 1) return original;
        var temp = new ArrayList<>(List.of(original));
        var tempString = "Can be equipped in the ";
        for (int i = 0; i < types.length - 1; i++) {
            tempString = tempString.concat(types[i] + ",");
        }
        tempString = tempString.concat(types[types.length - 1] + ".");
        temp.add(tempString);
        return temp.toArray(String[]::new);
    }
}
