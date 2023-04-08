package com.github.kydzombie.jubilant.inventory;

import com.github.kydzombie.jubilant.Jubilant;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;

public class InventorySatchel implements InventoryBase {
    private static final int DEFAULT_SIZE = 27;
    private final int size;

    private final ItemInstance[] inventory;

    public InventorySatchel() {
        size = DEFAULT_SIZE;

        inventory = new ItemInstance[size];
    }

    public InventorySatchel(ItemInstance satchel) {
        size = DEFAULT_SIZE;

        inventory = readData(satchel);
    }

    private ItemInstance[] readData(ItemInstance satchel) {
        var inventory = new ItemInstance[size];
        var items = satchel.getStationNBT().getListTag("inventory");
        for (var i = 0; i < items.size(); ++i) {
            var item = (CompoundTag) items.get(i);
            var slot = item.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = new ItemInstance(item);
            }
        }
        return inventory;
    }

    private void writeData(ItemInstance satchel, ItemInstance[] inventory) {
        var items = new ListTag();
        for (var i = 0; i < inventory.length; ++i) {
            if (inventory[i] == null) continue;
            var tag = new CompoundTag();
            tag.put("Slot", (byte) i);
            inventory[i].toTag(tag);
            items.add(tag);
        }

        satchel.getStationNBT().put("inventory", items);
    }

    @Override
    public int getInventorySize() {
        return size;
    }

    @Override
    public ItemInstance getInventoryItem(int i) {
        return inventory[i];
    }

    @Override
    public ItemInstance takeInventoryItem(int i, int count) {
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
            return var3;
        } else {
            return null;
        }
    }

    @Override
    public void setInventoryItem(int i, ItemInstance itemInstance) {
        inventory[i] = itemInstance;
        if (itemInstance != null && itemInstance.count > getMaxItemCount()) {
            itemInstance.count = getMaxItemCount();
        }
    }

    @Override
    public String getContainerName() {
        return "Satchel";
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
        return player.getHeldItem().getType() == Jubilant.SATCHEL;
    }
}
