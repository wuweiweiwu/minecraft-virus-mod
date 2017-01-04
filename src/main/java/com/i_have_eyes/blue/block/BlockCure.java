package com.i_have_eyes.blue.block;

import java.util.ArrayList;
import java.util.Random;

import com.i_have_eyes.blue.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockCure extends Block {
	
    protected BlockCure(String unlocalizedName, Material material) {    	
        super(material);
        this.setBlockName(unlocalizedName);
        this.setBlockTextureName(Main.MODID + ":"+ unlocalizedName);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(4.0F);
        this.setResistance(6.0F);
        this.setLightLevel(1.0F);
        this.setHarvestLevel("pickaxe", 4);
        this.setStepSound(soundTypeMetal);
        this.setTickRandomly(true);
    }
    
    //dropping multiple items when broken items
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(Items.baked_potato, world.rand.nextInt(10) + 1));
        return drops;
    }

    
    //tick rate
    //super fast so it can take over the virus
    public int tickRate(World p_149738_1_)
    {
        return 20;
    }

    //take over the virus
    public void updateTick(World world, int x, int y, int z, Random rand)
    {    	    	
    	cure(world,x,y,z,rand);
    }
    
    //cure function
    public void cure(World world, int x, int y, int z, Random rand)
    {
    	for (int i =0;i<3;i++)
		{
			for (int j=0;j<3;j++)
			{
				for (int k=0;k<3;k++)
				{
			    	if (world.getBlock(x+i-1, y+j-1, z+k-1).equals(ModBlocks.virus))
			    	{
			    		world.setBlock(x+i-1, y+j-1, z+k-1,this);
			    		world.scheduleBlockUpdate(x+i-1, y+j-1, z+k-1, this, this.tickRate(world) + rand.nextInt(10));
			    	}
				}
			}
		}
		world.setBlockToAir(x,y,z);
    }
    
    
    //generate spell particle effect lol
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
    	float f = (float)x + rand.nextFloat() * 0.1F;
        float f1 = (float)y + rand.nextFloat();
        float f2 = (float)z + rand.nextFloat();
        world.spawnParticle("spell", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
    }

 
}