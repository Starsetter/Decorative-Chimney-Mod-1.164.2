package DecorativeChimney.Blocks;

import java.util.List;
import java.util.Random;

import DecorativeChimney.CommonProxy;
import DecorativeChimney.DecorativeChimneyCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockMantelPlainSide extends Block
{
	public BlockMantelPlainSide(int id)
	{
		super(id, Material.rock);
		setHardness(5.0F);
    	setResistance(1.0F);
    	setStepSound(Block.soundStoneFootstep);
    	setUnlocalizedName("blockMantelPlainSide");
    	setCreativeTab(DecorativeChimneyCore.tabChimney);
	}

    @SideOnly(Side.CLIENT)
	public void getSubBlocks(int i, CreativeTabs creativetabs, List list)
    {
		for (int j = 0; j < 16; j++)
		{
			list.add(new ItemStack(this, 1, j));
		}
    }

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    private static final String[] blockMantelPlainSideNames =
		{ 
			"BlackMarble", "GrayMarble", "WhiteMarble", "BlackLargeBrick", "BlackSmallBrick", "StoneLargeBrick", "StoneSmallBrick", "Stone",
			"CobbleStone", "WhiteLargeBrick", "WhiteSmallBrick", "OakPlank", "Brick", "Emerald", "Gold", "Diamond"
		};

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
    	icons = new Icon[16];
    	
    	for(int i = 0; i < icons.length; i++)
    	{
    		ItemStack blockMantelPlainSideStack = new ItemStack(DecorativeChimneyCore.blockMantelPlainSide, 64, i);

    		icons[i] = iconregister.registerIcon(DecorativeChimneyCore.modid + ":" + blockMantelPlainSideNames[blockMantelPlainSideStack.getItemDamage()]);
    	}
    }
    	
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int meta)
    {
    	return icons[meta];
    }
    
    public int idDropped(int metadata, Random random)
    {
        return blockID;
    }

    public int damageDropped(int metadata)
    {
    	return metadata;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public int getRenderType()
	{
		return DecorativeChimneyCore.blockMantelPlainSideModelID;
	}

    public int getMobilityFlag()
    {
		return 0;    
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        boolean var5 = BlockMantelCorner.canConnectTo(iblockaccess, i, j, k - 1);
        boolean var6 = BlockMantelCorner.canConnectTo(iblockaccess, i, j, k + 1);
        boolean var7 = BlockMantelCorner.canConnectTo(iblockaccess, i - 1, j, k);
        boolean var8 = BlockMantelCorner.canConnectTo(iblockaccess, i + 1, j, k);
        float var9 = 0.3125F;
        float var10 = 0.6875F;
        float var11 = 0.3125F;
        float var12 = 0.6875F;

        if (var5)
        {
            var11 = 0.0F;
        }

        if (var6)
        {
            var12 = 1.0F;
        }

        if (var7)
        {
            var9 = 0.0F;
        }

        if (var8)
        {
            var10 = 1.0F;
        }

        setBlockBounds(var9, 0.0F, var11, var10, 1.0F, var12);
    }

    public void addCollidingBlockToList(World world, int i, int j, int k, AxisAlignedBB axisalignedBB, List list, Entity entity)
    {
        setBlockBoundsBasedOnState(world, i, j, k);
        super.addCollisionBoxesToList(world, i, j, k, axisalignedBB, list, entity);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.collisionRayTrace(world, x, y, z, start, end);
    }

	public static boolean renderMantelPlainSide(Block block, int i, int j, int k, RenderBlocks renderblocks, IBlockAccess iblockaccess)
	{
        boolean var5 = BlockMantelCorner.canConnectTo(renderblocks.blockAccess, i - 1, j, k);
        boolean var6 = BlockMantelCorner.canConnectTo(renderblocks.blockAccess, i + 1, j, k);
        boolean var7 = BlockMantelCorner.canConnectTo(renderblocks.blockAccess, i, j, k - 1);
        boolean var8 = BlockMantelCorner.canConnectTo(renderblocks.blockAccess, i, j, k + 1);
        boolean var9 = var5 && var6;
        boolean var10 = var7 && var8;
        boolean var11 = var5 && var6 && var7 && var8;

        if (var11)
        {
    		renderblocks.setRenderBounds(0.0F, 0.0F, 0.3125F, 1.0F, 1.0F, 0.6875F);
    		renderblocks.renderStandardBlock(block, i, j, k);

       		renderblocks.setRenderBounds(0.3125F, 0.0F, 0.0F, 0.6875F, 1.0F, 0.3125F);
       		renderblocks.renderStandardBlock(block, i, j, k);

       		renderblocks.setRenderBounds(0.3125F, 0.0F, 0.6875F, 0.6875F, 1.0F, 1.0F);
       		renderblocks.renderStandardBlock(block, i, j, k);
        }
        else
        {
        	if (var9 && !var10)
        	{
        		renderblocks.setRenderBounds(0.0F, 0.0F, 0.3125F, 1.0F, 1.0F, 0.6875F);
        		renderblocks.renderStandardBlock(block, i, j, k);
    		
        		if (var7)
        		{
        			renderblocks.setRenderBounds(0.3125F, 0.0F, 0.0F, 0.6875F, 1.0F, 0.3125F);
        			renderblocks.renderStandardBlock(block, i, j, k);
        		}
        		if (var8)
        		{
        			renderblocks.setRenderBounds(0.3125F, 0.0F, 0.6875F, 0.6875F, 1.0F, 1.0F);
        			renderblocks.renderStandardBlock(block, i, j, k);
        		}
        	}
        	else if (var10 && !var9)
        	{
        		renderblocks.setRenderBounds(0.3125F, 0.0F, 0.0F, 0.6875F, 1.0F, 1.0F);
        		renderblocks.renderStandardBlock(block, i, j, k);
        
        		if (var5)
        		{
        			renderblocks.setRenderBounds(0.0F, 0.0F, 0.3125F, 0.3125F, 1.0F, 0.6875F);
        			renderblocks.renderStandardBlock(block, i, j, k);
        		}
        		if (var6)
        		{
        			renderblocks.setRenderBounds(0.6875F, 0.0F, 0.3125F, 1.0F, 1.0F, 0.6875F);
        			renderblocks.renderStandardBlock(block, i, j, k);
        		}
        	}        
        	else if (var5 && !var6 && !var10)
        	{
        		renderblocks.setRenderBounds(0.0F, 0.0F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
        		renderblocks.renderStandardBlock(block, i, j, k);

        		if (var7 && !var8)
        		{
        			renderblocks.setRenderBounds(0.3125F, 0.0F, 0.0F, 0.6875F, 1.0F, 0.3125F);
        			renderblocks.renderStandardBlock(block, i, j, k);
        		}
        		if (var8 && !var7)
        		{
        			renderblocks.setRenderBounds(0.3125F, 0.0F, 0.6875F, 0.6875F, 1.0F, 1.0F);
        			renderblocks.renderStandardBlock(block, i, j, k);
        		}
        	}
        	else if (var6 && !var5 && !var10)
        	{
        		renderblocks.setRenderBounds(0.3125F, 0.0F, 0.3125F, 1.0F, 1.0F, 0.6875F);
  	    	  	renderblocks.renderStandardBlock(block, i, j, k);

  	    	  	if (var7 && !var8)
  	    	  	{
  	    	  		renderblocks.setRenderBounds(0.3125F, 0.0F, 0.0F, 0.6875F, 1.0F, 0.3125F);
  	    	  		renderblocks.renderStandardBlock(block, i, j, k);
  	    	  	}
  	    	  	if (var8 && !var7)
  	    	  	{
  	    	  		renderblocks.setRenderBounds(0.3125F, 0.0F, 0.6875F, 0.6875F, 1.0F, 1.0F);
  	    	  		renderblocks.renderStandardBlock(block, i, j, k);
  	    	  	}
        	}
        	else if (var7 && !var8 && !var9)
        	{
        		renderblocks.setRenderBounds(0.3125F, 0.0F, 0.0F, 0.6875F, 1.0F, 0.6875F);
        		renderblocks.renderStandardBlock(block, i, j, k);
        	}

        	else if (var8 && !var7 && !var9)
        	{
        		renderblocks.setRenderBounds(0.3125F, 0.0F, 0.3125F, 0.6875F, 1.0F, 1.0F);
        		renderblocks.renderStandardBlock(block, i, j, k);
        	}
        	else
        	{
        		renderblocks.setRenderBounds(0.3125F, 0.0F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
        		renderblocks.renderStandardBlock(block, i, j, k);
        	}
        }
        
        renderblocks.clearOverrideBlockTexture();
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        return true;
    }
        
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return true;
    }
}
