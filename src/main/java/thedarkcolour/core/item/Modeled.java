package thedarkcolour.core.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thedarkcolour.futuremc.init.InitElements;

/**
 * Used to load models.
 */
public interface Modeled {
    @SideOnly(Side.CLIENT)
    default void model() {
        ModelLoader.setCustomModelResourceLocation((Item) this, 0, new ModelResourceLocation(((Item) this).getRegistryName(), "inventory"));
    }

    default void addModel() {
        InitElements.MODELED.add(this);
    }
}
