package com.github.kydzombie.jubilant.inventory;

import com.github.kydzombie.jubilant.Jubilant;
import com.github.kydzombie.jubilant.item.Parchment;
import com.github.kydzombie.jubilant.item.Quill;
import com.github.kydzombie.jubilant.spell.SpellRegistry;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;

public class InventoryDave implements InventoryBase {
    public static final int QUILL_SLOT = 0;
    public static final int PARCHMENT_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;
    private final ItemInstance[] inventory = new ItemInstance[3];
    private final ItemInstance dave;

    public InventoryDave() {
        dave = null;
    }

    public InventoryDave(ItemInstance dave) {
        this.dave = dave;

        var items = dave.getStationNBT().getListTag("inventory");
        for (var i = 0; i < items.size(); ++i) {
            var item = (CompoundTag) items.get(i);
            var slot = item.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = new ItemInstance(item);
            }
        }
    }

    public void writeData() {
        var items = new ListTag();
        for (var i = 0; i < inventory.length - 1; ++i) {
            if (inventory[i] == null) continue;
            var tag = new CompoundTag();
            tag.put("Slot", (byte) i);
            inventory[i].toTag(tag);
            items.add(tag);
        }

        dave.getStationNBT().put("inventory", items);
    }

    private boolean checkRecipe() {
        var quill = inventory[QUILL_SLOT];
        var parchment = inventory[PARCHMENT_SLOT];
        if (quill == null || parchment == null) return false;
        if (quill.getType() == Jubilant.ENCHANTED_QUILL && parchment.getType() == Jubilant.PARCHMENT) {
            return true;
        }
        return false;
    }

    private void updateOutput() {
        if (checkRecipe()) {
            System.out.println("Added Thingy");
            var inscribedParchment = new ItemInstance(Jubilant.INSCRIBED_PARCHMENT);
            inscribedParchment.getStationNBT().put("spell", "jubilant:fire");
            inventory[OUTPUT_SLOT] = inscribedParchment;
        } else {
            System.out.println("Removed thingy");
            inventory[OUTPUT_SLOT] = null;
        }
    }

    @Override
    public int getInventorySize() {
        return 2;
    }

    @Override
    public ItemInstance getInventoryItem(int i) {
        return inventory[i];
    }

    @Override
    public ItemInstance takeInventoryItem(int i, int count) {
        if (i == OUTPUT_SLOT && checkRecipe()) {
            inventory[QUILL_SLOT].applyDamage(1, null);
            inventory[PARCHMENT_SLOT].count--;
            updateOutput();
        }
        if (inventory[i] != null) {
            ItemInstance var3;
            if (inventory[i].count <= count) {
                var3 = inventory[i];
                inventory[i] = null;
            } else {
                var3 = inventory[i].split(count);
                if (inventory[i].count == 0) {
                    inventory[i] = null;
                }
            }
            updateOutput();
            return var3;
        } else {
            updateOutput();
            return null;
        }
    }

    @Override
    public void setInventoryItem(int i, ItemInstance itemInstance) {
        inventory[i] = itemInstance;
        if (itemInstance != null && itemInstance.count > getMaxItemCount()) {
            itemInstance.count = getMaxItemCount();
        }

        updateOutput();
    }

    @Override
    public String getContainerName() {
        return "Dave";
    }

    @Override
    public int getMaxItemCount() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean canPlayerUse(PlayerBase player) {
        return player.getHeldItem() != null && player.getHeldItem().getType() == Jubilant.DAVE;
    }
}
