package com.i_have_eyes.blue;

import com.i_have_eyes.blue.block.ModBlocks;
import com.i_have_eyes.blue.item.ModItems;
import com.i_have_eyes.blue.recipe.ModRecipes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Main.MODID, version = Main.VERSION, name = Main.NAME)
public class Main {

	public static final String MODID = "blue";
    public static final String VERSION = "1.0";
    public static final String NAME = "Blue Stuff";
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        ModBlocks.init();
        ModItems.init();
        ModRecipes.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
    	
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
    	
    }
    
	
}
