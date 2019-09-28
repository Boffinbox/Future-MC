package thedarkcolour.futuremc.container;

import invtweaks.api.container.ChestContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import thedarkcolour.core.gui.Container;
import thedarkcolour.futuremc.client.gui.GuiBarrel;
import thedarkcolour.futuremc.tile.TileBarrel;

@ChestContainer
public class ContainerBarrel extends Container {
    public final TileBarrel te;
    public final InventoryPlayer playerInventory;

    public ContainerBarrel(InventoryPlayer playerInv, TileEntity te) {
        this.te = (TileBarrel) te;
        this.playerInventory = playerInv;

        addOwnSlots();
        addPlayerSlots(playerInventory);
    }

    private void addPlayerSlots(IInventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 9 + col * 18 - 1;
                int y = row * 18 + 70 + 15;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, x, y));
            }
        }

        // Slots for the hotBar
        for (int row = 0; row < 9; ++row) {
            int x = 9 + row * 18 - 1;
            int y = 58 + 70 + 15;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }
    }

    private void addOwnSlots() {
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        int slotIndex = 0;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = 18 + row * 18;
                addSlotToContainer(new SlotItemHandler(itemHandler, slotIndex, x, y));
                slotIndex++;
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemstack = itemStack1.copy();

            if (index < 27) {
                if (!this.mergeItemStack(itemStack1, 27, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemStack1, 0, 27, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.getDistanceSq(te.getPos().add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @SideOnly(Side.CLIENT)
    public GuiContainer getGuiContainer() {
        return new GuiBarrel(new ContainerBarrel(playerInventory, te));
    }
}