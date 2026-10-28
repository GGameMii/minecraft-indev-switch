/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.client.gui.inventory;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.inventory.BuildScreen;
import net.minecraft.network.User;
import net.minecraft.world.container.Container;
import net.minecraft.world.container.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ContainerCreative
extends Container {
    protected List<ItemStack> creativeItems = new ArrayList<ItemStack>();

    public ContainerCreative(Player player) {
        int i2 = 0;
        while (i2 < User.allowedTiles.size()) {
            this.creativeItems.add(new ItemStack(User.allowedTiles.get(i2)));
            ++i2;
        }
        i2 = 0;
        while (i2 < Item.itemsList.length) {
            if (Item.itemsList[i2] != null && i2 > 256) {
                this.creativeItems.add(new ItemStack(Item.itemsList[i2]));
            }
            ++i2;
        }
        int j3 = 0;
        while (j3 < 9) {
            int l3 = 0;
            while (l3 < 8) {
                this.addSlot(new Slot(BuildScreen.getInventory(), l3 + j3 * 8, 8 + l3 * 18, 18 + j3 * 18));
                ++l3;
            }
            ++j3;
        }
        int k3 = 0;
        while (k3 < 9) {
            this.addSlot(new Slot(player.inventory, k3, 8 + k3 * 18, 184));
            ++k3;
        }
        this.scrollTo(0.0f);
    }

    public void scrollTo(float par1) {
        int i2 = this.creativeItems.size() / 8 - 8 + 1;
        int j2 = (int)((double)(par1 * (float)i2) + 0.5);
        if (j2 < 0) {
            j2 = 0;
        }
        int k2 = 0;
        while (k2 < 9) {
            int l2 = 0;
            while (l2 < 8) {
                int i1 = l2 + (k2 + j2) * 8;
                if (i1 >= 0 && i1 < this.creativeItems.size()) {
                    BuildScreen.getInventory().setInventorySlotContents(l2 + k2 * 8, this.creativeItems.get(i1));
                } else {
                    BuildScreen.getInventory().setInventorySlotContents(l2 + k2 * 8, null);
                }
                ++l2;
            }
            ++k2;
        }
    }

    @Override
    public boolean isUsableByPlayer(Player var1) {
        return !var1.level.survivalWorld;
    }
}

