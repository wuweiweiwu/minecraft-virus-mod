package com.i_have_eyes.blue.block;

import com.i_have_eyes.blue.item.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class ModBlocks {

    public static Block virus;
    public static Block cure;
    public static Block item_rep;

    public static final void init() {
    	virus = new BlockVirus("virus", Material.iron);
        cure = new BlockCure("cure", Material.iron);
        item_rep = new BlockItemReplicator("item_rep", Material.iron);
        
        GameRegistry.registerBlock(virus, "virus");
        GameRegistry.registerBlock(cure, "cure");
        GameRegistry.registerBlock(item_rep, "item_rep");
    }

}