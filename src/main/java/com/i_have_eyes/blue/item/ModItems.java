package com.i_have_eyes.blue.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

	public static Item infectedSword;
	public static ToolMaterial MATERIAL;

    public static final void init() {
    	
    	//breaks after 10 uses
    	MATERIAL = EnumHelper.addToolMaterial("MATERIAL", 3, 10, 15.0F, 8.0F, 30);
    	
    	infectedSword = new ItemInfectedSword("infected_sword", MATERIAL);
    	
    	GameRegistry.registerItem(infectedSword, "infected_sword");
    }
	
}
