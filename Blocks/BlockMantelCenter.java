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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockMantelCenter extends Block
{
	public BlockMantelCenter(int id)
	{
		super(id, Material.rock);
		setHardness(5.0F);
    	setResistance(1.0F);
    	setStepSound(Block.soundStoneFootstep);
    	setUnlocalizedName("blockMantelCenter");
    	setCreativeTab(DecorativeChimneyCore.tabChimney);
	}

    @SideOnly(Side.CLIENT)
	public void getSubBlocks(int i, CreativeTabs creativetabs, List list)
    {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 4));
		list.add(new ItemStack(this, 1, 8));
		list.add(new ItemStack(this, 1, 12));
    }

    @SideOnly(Side.CLIENT)
    private static Icon[] icons;

    @SideOnly(Side.CLIENT)
    private static Icon[] icons2;

    private static final String[] blockMantelCenterNames =
		{ 
    		"WhiteMarble", "WhiteMarble", "WhiteMarble", "WhiteMarble", "WhiteMarble", "WhiteMarble", "WhiteMarble", "WhiteMarble",
    		"GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble"
		};

    private static final String[] blockMantelCenterSecondaryNames =
		{ 
			"GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", "BlackMarble", "BlackMarble", "BlackMarble", "BlackMarble",
			"WhiteMarble", "WhiteMarble", "WhiteMarble", "WhiteMarble", "BlackMarble", "BlackMarble", "BlackMarble", "BlackMarble"
		};

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
    	icons = new Icon[16];
    	icons2 = new Icon[16];
    	
    	for(int i = 0; i < 16; i++)
    	{
    		ItemStack blockMantelCenterStack = new ItemStack(DecorativeChimneyCore.blockMantelCenter, 64, i);

    		icons[i] = iconregister.registerIcon(DecorativeChimneyCore.modid + ":" + blockMantelCenterNames[blockMantelCenterStack.getItemDamage()]);

    		icons2[i] = iconregister.registerIcon(DecorativeChimneyCore.modid + ":" + blockMantelCenterSecondaryNames[blockMantelCenterStack.getItemDamage()]);
    	}
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int i, int meta)
    {
    	if(i == 6)
    	{
    		return icons[meta];
    
    	}
    	if(i == 7)
    	{
    		return icons2[meta];
    	}
    	return icons[meta];
    }
    public int idDropped(int metadata, Random random)
    {
        return blockID;
    }

    public int damageDropped(int metadata)
    {
    	return metadata / 4;
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

	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemstack)
	{
		int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(i, j, k, world.getBlockMetadata(i, j, k) + l, 2);
	}
	
	public int getRenderType()
	{
		return DecorativeChimneyCore.blockMantelCenterModelID;
	}

    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
        if (world.doesBlockHaveSolidTopSurface(x, y, z))
        {
            return true;
        }
        else
        {
            int id = world.getBlockId(x, y, z);
            return id == DecorativeChimneyCore.blockMantelCenter.blockID;
        }
    }

    public int getMobilityFlag()
    {
		return 0;    
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
    	boolean var5 = BlockMantelCorner.canConnectTo(iblockaccess, i - 1, j, k);
        boolean var6 = BlockMantelCorner.canConnectTo(iblockaccess, i + 1, j, k);
        boolean var7 = BlockMantelCorner.canConnectTo(iblockaccess, i, j, k - 1);
        boolean var8 = BlockMantelCorner.canConnectTo(iblockaccess, i, j, k + 1);
        float var9 = 0.3125F;
        float var10 = 0.6875F;

    	for(int l = 0; l < 16; l = l + 2)
    	{
        	for(int m = 1; m < 16; m = m + 2)
        	{
        		if(iblockaccess.getBlockMetadata(i, j, k) == l)
        		{
        			if(var7)
        			{
        				var9 = 0.0F;
        			}

        			if(var8)
        			{
        				var10 = 1.0F;
        			}

        			setBlockBounds(0.0F, 0.125F, var9, 1.0F, 1.0F, var10);
        		}
        		else if(iblockaccess.getBlockMetadata(i, j, k) == m)
        		{
        			if(var5)
        			{
        				var9 = 0.0F;
        			}

        			if(var6)
        			{
        				var10 = 1.0F;
        			}
        	
        			setBlockBounds(var9, 0.125F, 0.0F, var10, 1.0F, 1.0F);
        		}
        	}
        }
    }

    public void addCollidingBlockToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
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

    public static boolean renderMantelCenter(Block block, int i, int j, int k, RenderBlocks renderblocks, IBlockAccess iblockaccess)
    {
        boolean var5 = BlockMantelCorner.canConnectTo(renderblocks.blockAccess, i - 1, j, k);
        boolean var6 = BlockMantelCorner.canConnectTo(renderblocks.blockAccess, i + 1, j, k);
        boolean var7 = BlockMantelCorner.canConnectTo(renderblocks.blockAccess, i, j, k - 1);
        boolean var8 = BlockMantelCorner.canConnectTo(renderblocks.blockAccess, i, j, k + 1);
        boolean var14 = BlockMantelCorner.canConnectTo(renderblocks.blockAccess, i, j - 1, k);
        boolean var9 = var5 && var6;
        boolean var10 = var7 && var8;
        boolean var13 = var5 && var6 && var7 && var8;

        float par1 = 0.0F;
        float par2 = 0.0625F;
        float par3 = 1.0F;
        float par4 = 0.9375F;
        float par5 = 0.0F;
        float par6 = 0.125F;
        float par7 = 1.0F;
        float par8 = 0.875F;
        float par9 = 0.0F;
        float par10 = 0.1875F;
        float par11 = 1.0F;
        float par12 = 0.8125F;
        float par13 = 0.0F;
        float par14 = 0.3125F;
        float par15 = 1.0F;
        float par16 = 0.6875F;
        float par17 = 0.0F;
        float par18 = 0.3125F;
        float par19 = 0.0625F;
        float par20 = 0.6875F;
        float par21 = 0.9375F;
        float par22 = 0.3125F;
        float par23 = 1.0F;
        float par24 = 0.6875F;
        float par25 = 0.0F;
        float par26 = 0.3125F;
        float par27 = 1.0F;
        float par28 = 0.6875F;
        float par29 = 0.0F;
        float par30 = 0.25F;
        float par31 = 1.0F;
        float par32 = 0.75F;
        float par33 = 0.0625F;
        float par34 = 0.375F;
        float par35 = 0.9375F;
        float par36 = 0.625F;

        for(int l = 0; l < 16; l = l + 2)
        {
        	for(int m = 1; m < 16; m = m + 2)
        	{
        		if(iblockaccess.getBlockMetadata(i, j, k) == l || iblockaccess.getBlockMetadata(i, j, k) == m)
        		{
        			if (var13)
        			{
        				renderblocks.setRenderBounds(0.0F, 0.125F, 0.0F, 1.0F, 1.0F, 1.0F);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			}
        			else if(iblockaccess.getBlockMetadata(i, j, k) == l && var10 && !var9)
        			{
        				renderblocks.setRenderBounds(0.0F, 0.125F, 0.0F, 1.0F, 1.0F, 1.0F);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			}
        			else if(iblockaccess.getBlockMetadata(i, j, k) == m && var9 && !var10)
        			{
        				renderblocks.setRenderBounds(0.0F, 0.125F, 0.0F, 1.0F, 1.0F, 1.0F);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			}
        			else
        			{
        				int n = l;
    					if(iblockaccess.getBlockMetadata(i, j, k) == m)
    					{
    						n = m;
    						par1 = 0.0625F;
    						par2 = 0.0F;
    						par3 = 0.9375F;
    						par4 = 1.0F;
    						par5 = 0.125F;
    						par6 = 0.0F;
    						par7 = 0.875F;
    						par8 = 1.0F;
    						par9 = 0.1875F;
    						par10 = 0.0F;
    						par11 = 0.8125F;
    						par12 = 1.0F;
    						par13 = 0.3125F;
    						par14 = 0.0F;
    						par15 = 0.6875F;
    						par16 = 1.0F;
    						par17 = 0.3125F;
    						par18 = 0.0F;
    						par19 = 0.6875F;
    						par20 = 0.0625F;
    						par21 = 0.3125F;
    						par22 = 0.9375F;
    						par23 = 0.6875F;
    						par24 = 1.0F;
    						par25 = 0.3125F;
    						par26 = 0.0F;
    						par27 = 0.6875F;
    						par28 = 1.0F;
    						par29 = 0.25F;
    						par30 = 0.0F;
    						par31 = 0.75F;
    						par32 = 1.0F;
    						par33 = 0.375F;
    						par34 = 0.0625F;
    						par35 = 0.625F;
    						par36 = 0.9375F;

        					if(var5 && !var6)
        					{
        						par1 = 0.0F;
        						par5 = 0.0F;
        						par9 = 0.0F;
        						par13 = 0.0F;
        						par17 = 0.0F;
        						par21 = 0.0F;
        						par25 = 0.0F;
        						par29 = 0.0F;
        						par33 = 0.0F;
        					}
        					else if(var6 && !var5)
        					{
        						par3 = 1.0F;
        						par7 = 1.0F;
        						par11 = 1.0F;
        						par15 = 1.0F;
        						par19 = 1.0F;
        						par23 = 1.0F;
        						par27 = 1.0F;
        						par31 = 1.0F;
        						par35 = 1.0F;
        					}
    					}
        				if(iblockaccess.getBlockMetadata(i, j, k) == l)
        				{
        					if(var7 && !var8)
        					{
        						par2 = 0.0F;
        						par6 = 0.0F;
        						par10 = 0.0F;
        						par14 = 0.0F;
        						par18 = 0.0F;
        						par22 = 0.0F;
        						par26 = 0.0F;
        						par30 = 0.0F;
        						par34 = 0.0F;
        					}
        					else if(var8 && !var7)
        					{
        						par4 = 1.0F;
        						par8 = 1.0F;
        						par12 = 1.0F;
        						par16 = 1.0F;
        						par20 = 1.0F;
        						par24 = 1.0F;
        						par28 = 1.0F;
        						par32 = 1.0F;
        						par36 = 1.0F;
        					}
		        		}
        			//Top
        				renderblocks.setRenderBounds(0.0F, 0.9375F, 0.0F, 1.0F, 1.0F, 1.0F);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			//Level 1
        				renderblocks.setRenderBounds(par1, 0.875F, par2, par3, 0.9375F, par4);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			//Level 2
        				renderblocks.setRenderBounds(par5, 0.75F, par6, par7, 0.875F, par8);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			//Level 3
        				renderblocks.setRenderBounds(par9, 0.6875F, par10, par11, 0.75F, par12);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			//Level 4
        				renderblocks.setRenderBounds(par13, 0.5625F, par14, par15, 0.6875F, par16);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			//Level 5
        			//Side 1
        				renderblocks.setRenderBounds(par17, 0.3125F, par18, par19, 0.5625F, par20);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			//Side 2
        				renderblocks.setRenderBounds(par21, 0.3125F, par22, par23, 0.5625F, par24);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			//Level 6
        				renderblocks.setRenderBounds(par25, 0.1875F, par26, par27, 0.3125F, par28);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			//Level 7
        				renderblocks.setRenderBounds(par29, 0.125F, par30, par31, 0.1875F, par32);
        				renderblocks.renderStandardBlock(block, i, j, k);
        			//Core
        				renderblocks.overrideBlockTexture = block.getIcon(7, n);
        				renderblocks.setRenderBounds(par33, 0.3125F, par34, par35, 0.5625F, par36);
        				renderblocks.renderStandardBlock(block, i, j, k);
        				renderblocks.clearOverrideBlockTexture();	
		        	}
	        	}
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
