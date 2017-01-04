package com.i_have_eyes.blue.block;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import com.i_have_eyes.blue.Main;
import com.i_have_eyes.blue.item.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.vecmath.Point3d;

public class BlockVirus extends Block {
	
    protected BlockVirus(String unlocalizedName, Material material) {    	
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
        drops.add(new ItemStack(Items.coal, world.rand.nextInt(3) + 1));
        drops.add(new ItemStack(Items.iron_ingot, world.rand.nextInt(2) + 1));
        drops.add(new ItemStack(Items.gold_ingot, world.rand.nextInt(2) + 1));
        drops.add(new ItemStack(Items.diamond, world.rand.nextInt(3) + 2, 4));
        drops.add(new ItemStack(Items.redstone, world.rand.nextInt(2) + 2));
        if (world.rand.nextFloat() < 0.5F)
            drops.add(new ItemStack(Items.emerald));
        return drops;
    }
    
    //what happens when right click
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {

    	//if holding flint and steel then explode
    	if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
        {
        	Explosion explosion = new Explosion(world,null,x,y,z,10);
        	explosion.doExplosionA();
        	explosion.doExplosionB(true);  
        }
    	//it you hit it with blaze rod then replaces it with an ice block
    	else if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.blaze_rod)
    	{
    		world.setBlock(x, y, z, Blocks.coal_ore);
    	}
    	//hit it with a diamond to give 3x3 diamond blocks
    	else if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.diamond)
    	{
    		for (int i =0;i<3;i++)
    		{
    			for (int j=0;j<3;j++)
    			{
    				for (int k=0;k<3;k++)
    				{
    		    		world.setBlock(x+i-1,y+j-1,z+k-1, Blocks.diamond_block);
    				}
    			}
    		} 
    	}
    	//hit it with a carrot to spawn a pig
    	else if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.carrot)
    	{
    		if (!world.isRemote)
    		{
	    		EntityPig pig = new EntityPig(world);
	        	pig.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
	        	world.spawnEntityInWorld(pig);
	        	world.setBlockToAir(x, y, z);
    		}
    	}
    	//if holding the infected sword will turn it into the cure block
    	else if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == ModItems.infectedSword)
    	{
    		world.setBlock(x, y, z, ModBlocks.cure);
    	}
    	//just place the block
    	else
        {
            return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
        }

    	return true;
    }
    
    //explode if it is destroyed by an explosion
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion p_149723_5_)
    {
        if (!world.isRemote)
        {
        	Explosion explosion = new Explosion(world,null,x,y,z,5);
        	explosion.doExplosionA();
        	explosion.doExplosionB(true);  
        }
    }
    
    //if it will drop itself when destroyed from explosion
    public boolean canDropFromExplosion(Explosion p_149659_1_)
    {
        return false;
    }
    
    //tick rate
    //a little slower than fire
    public int tickRate(World p_149738_1_)
    {
        return 60;
    }
    
    //autogenerate one block at random in the blocks around its 3x3x3 cube that ISNT empty... i.e air. cant go through bedrock
    public void updateTick(World world, int x, int y, int z, Random rand)
    {    	    	
    	ArrayList<Point3d> coorList = new ArrayList<Point3d>();
    	for (int i =0;i<3;i++)
		{
			for (int j=0;j<3;j++)
			{
				for (int k=0;k<3;k++)
				{
			    	if ((! world.getBlock(x+i-1, y+j-1, z+k-1).equals(Blocks.air)) &&
			    			(! world.getBlock(x+i-1, y+j-1, z+k-1).equals(Blocks.bedrock)) &&
			    			(! world.getBlock(x+i-1, y+j-1, z+k-1).equals(ModBlocks.cure)) &&
			    			(i+j+k != 3))
			    	{
			    		//create point and add to arraylist if it isnt air and isnt itself
			    		coorList.add(new Point3d(x+i-1, y+j-1, z+k-1));
			    	}
				}
			}
		}

    	if (coorList.size() != 0){
        	//randomly choose a point from the list of viable coordinates
        	int randint = rand.nextInt(coorList.size());
        	Point3d p = coorList.get(randint);
        	
    		world.setBlock((int) p.x, (int) p.y, (int) p.z,this);
    		world.scheduleBlockUpdate((int) p.x, (int) p.y, (int) p.z, this, this.tickRate(world) + rand.nextInt(10));
    	}
    }
    
    //helper function to see if we are around water in the 6 direct faces
    public boolean isAroundWater(World world, int x, int y, int z)
    {
    	Material neighbor = world.getBlock(x+1, y, z).getMaterial();
    	if (neighbor.equals(Material.water))
    	{
    		return true;
    	}
    	neighbor = world.getBlock(x-1, y, z).getMaterial();
    	if (neighbor.equals(Material.water))
    	{
    		return true;
    	}
    	neighbor = world.getBlock(x, y+1, z).getMaterial();
    	if (neighbor.equals(Material.water))
    	{
    		return true;
    	}
    	neighbor = world.getBlock(x, y-1, z).getMaterial();
    	if (neighbor.equals(Material.water))
    	{
    		return true;
    	}
    	neighbor = world.getBlock(x, y, z+1).getMaterial();
    	if (neighbor.equals(Material.water))
    	{
    		return true;
    	}
    	neighbor = world.getBlock(x+1, y, z-1).getMaterial();
    	if (neighbor.equals(Material.water))
    	{
    		return true;
    	}
    	return false;
    }
    
    //generate a pigmen when an arrow hits it collides
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent)
    {
        if (!world.isRemote && ent instanceof EntityArrow)
        {
        	EntityPigZombie zombie = new EntityPigZombie(world);
        	zombie.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
        	world.spawnEntityInWorld(zombie);
        	world.setBlockToAir(x, y, z);
        }
    }
    
    //generate smoke lol
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
    	float f = (float)x + rand.nextFloat() * 0.1F;
        float f1 = (float)y + rand.nextFloat();
        float f2 = (float)z + rand.nextFloat();
        world.spawnParticle("largesmoke", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
    }
    
    //when the neighborblocks change call this method
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
    	if (isAroundWater(world, x, y, z))
        {
            world.setBlockToAir(x, y, z); 
        }
    }
    
    //when the block is added this function is called
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        if (isAroundWater(world, x, y, z))
        {
            world.setBlockToAir(x, y, z); 
        }
       
    } 
}