package com.i_have_eyes.blue.block;

import java.util.Random;

import com.i_have_eyes.blue.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockItemReplicator extends Block {

	protected BlockItemReplicator(String unlocalizedName, Material material) {    	
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
	
	/**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	if (player.getCurrentEquippedItem() != null)
    	{
    		//generate output
    		if (!world.isRemote){
    			ItemStack itemStack = new ItemStack(player.getHeldItem().getItem(),64);
    			EntityItem itemEntity = new EntityItem(world, x, y, z, itemStack);
        		world.spawnEntityInWorld(itemEntity);
        		
        		//spawn zombie
        		EntityZombie zomb = new EntityZombie(world);
	        	zomb.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
	        	world.spawnEntityInWorld(zomb);
	        	zomb = new EntityZombie(world);
	        	zomb.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
	        	world.spawnEntityInWorld(zomb);
    		}
    		
    		//decrease player health to 1 heart
    		player.setHealth(2.0F);
    		
    		//randomly determine if we will generate a virus block
    		//15% chance of creating a vein of virus blocks
    		if (Math.random() < .15)
    		{
    			for (int i =0;i< 5; i++)
    			{
    				world.setBlock(x, y-30-i, z,ModBlocks.virus);
    			}
    			
    			for (int i=0;i<7;i++){
    				for(int j=0;j<7;j++){
    					world.setBlock(x-3+i, y-1, z-3+j, Blocks.obsidian);
    				}
    			}
    			world.setBlock(x,y,z,Blocks.glass);
    		}
    		
    	}
    	//just place the block
    	else
        {
            return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
        }

    	return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
    	float f = (float)x + rand.nextFloat() * 0.1F;
        float f1 = (float)y + rand.nextFloat();
        float f2 = (float)z + rand.nextFloat();
        world.spawnParticle("portal", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
    }
}