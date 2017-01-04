package com.i_have_eyes.blue.item;

import net.minecraft.item.ItemSword;

import com.i_have_eyes.blue.Main;

public class ItemInfectedSword extends ItemSword{

	public ItemInfectedSword(String unlocalizedName, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(Main.MODID + ":" + unlocalizedName);
	}

	
}
