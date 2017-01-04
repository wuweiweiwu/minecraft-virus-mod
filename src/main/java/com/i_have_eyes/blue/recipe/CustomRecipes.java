package com.i_have_eyes.blue.recipe;

import com.i_have_eyes.blue.block.ModBlocks;
import com.i_have_eyes.blue.item.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class CustomRecipes{

	//infectedsword recipe
	public static Object[] getInfectedSwordIngredients(){
		return new Object[] {"#x#", "#x#", " I ", '#', Blocks.diamond_block, 'I',Items.diamond_sword,'x',Blocks.gold_block};
	}
	
	public static ItemStack getInfectedSwordFinalItem(){
		return new ItemStack(ModItems.infectedSword);
	}
	
	//item replicator recipe
	public static Object[] getItemReplicatorIngredients(){
		return new Object[] {"###","#I#","###",'#', Blocks.netherrack, 'I',Items.ender_pearl};
	}
	
	public static ItemStack getItemReplicatorFinalItem(){
		return new ItemStack(ModBlocks.item_rep);
	}
}
