package de.vepexlegit.inventorymovedebug;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = InventoryMoveDebug.MODID, version = InventoryMoveDebug.VERSION)
public class InventoryMoveDebug {
    public static final String MODID = "inventorymovedebug";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName());
    }
}
