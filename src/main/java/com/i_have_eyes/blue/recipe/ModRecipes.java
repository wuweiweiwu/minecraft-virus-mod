package com.i_have_eyes.blue.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import com.i_have_eyes.blue.recipe.*;

public class ModRecipes {
	
	public static final void init() {
		
		//add infectedswordrecipe
		GameRegistry.addRecipe(CustomRecipes.getInfectedSwordFinalItem(),CustomRecipes.getInfectedSwordIngredients());
		
		//add itemrep
		GameRegistry.addRecipe(CustomRecipes.getItemReplicatorFinalItem(), CustomRecipes.getItemReplicatorIngredients());
		
	}
}
