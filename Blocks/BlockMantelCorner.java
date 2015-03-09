package DecorativeChimney.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecorativeChimney.CommonProxy;
import DecorativeChimney.DecorativeChimneyCore;
import DecorativeChimney.TileEntities.TileEntityColor;

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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockMantelCorner extends BlockContainer
{
	public BlockMantelCorner(int id, Class class1)
	{
    	super(id, Material.rock);
		anEntityClass = class1;
		setHardness(5.0F);
    	setResistance(1.0F);
    	setStepSound(Block.soundStoneFootstep);
    	setUnlocalizedName("blockMantelCorner");
    }

    @SideOnly(Side.CLIENT)
    private static Icon[] icons;

    @SideOnly(Side.CLIENT)
    private static Icon[] icons2;

    private static final String[] blockMantelSideNames =
		{ 
    		"BlackMarble", "BlackMarble", "GrayMarble", "GrayMarble",
    		"WhiteMarble", "WhiteMarble", "OakPlank", "Stone",
    		"CobbleStone", "Emerald", "Gold", "Diamond"
		};

    private static final String[] blockManteSideSecondaryNames =
		{ 
    		"GrayMarble", "WhiteMarble", "BlackMarble", "WhiteMarble",
    		"BlackMarble", "GrayMarble", "OakPlank", "Stone",
			"CobbleStone", "Emerald", "Gold", "Diamond"
		};

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
    	icons = new Icon[12];
    	icons2 = new Icon[12];
    	
    	for(int i = 0; i < blockMantelSideNames.length; i++)
    	{
    		ItemStack blockMantelSideStack = new ItemStack(DecorativeChimneyCore.blockMantelSide, 64, i);

    		icons[i] = iconRegister.registerIcon(DecorativeChimneyCore.modid + ":" + blockMantelSideNames[blockMantelSideStack.getItemDamage()]);

    		icons2[i] = iconRegister.registerIcon(DecorativeChimneyCore.modid + ":" + blockManteSideSecondaryNames[blockMantelSideStack.getItemDamage()]);
    	}
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int i, int metaData)
    {
    	if(i == 6)
    	{
    		return icons[metaData];
    
    	}
    	if(i == 7)
    	{
    		return icons2[metaData];
    	}
    	return icons[metaData];
    }
    
    public void onBlockHarvested(World world, int x, int y, int z, int metaData, EntityPlayer entityPlayer)
    {
        if (entityPlayer.capabilities.isCreativeMode)
        {
            metaData |= 8;
            world.setBlockMetadataWithNotify(x, y, z, metaData, 4);
        }

        dropBlockAsItem(world, x, y, z, metaData, 0);

        super.onBlockHarvested(world, x, y, z, metaData, entityPlayer);
    }

    public void breakBlock(World world, int x, int y, int z, int id, int metaData)
    {
        super.breakBlock(world, x, y, z, id, metaData);
    }

    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if ((metadata & 8) == 0)
        {
            ItemStack itemstack = new ItemStack(DecorativeChimneyCore.itemMantelCorner.itemID, 1, this.getDamageValue(world, x, y, z));
            TileEntityColor tileEntityColor = (TileEntityColor)world.getBlockTileEntity(x, y, z);

            if (tileEntityColor == null)
            {
                return drops;
            }
            drops.add(itemstack);
        }
        return drops;
    }

    public int idPicked(World world, int x, int y, int z)
    {
        return DecorativeChimneyCore.itemMantelCorner.itemID;
    }

    public int idDropped(int metaData, Random random)
    {
        return DecorativeChimneyCore.itemMantelCorner.itemID;
    }

    public int getDamageValue(World world, int x, int y, int z)
    {
        TileEntity tileentity = world.getBlockTileEntity(x, y, z);
        return tileentity != null && tileentity instanceof TileEntityColor ? ((TileEntityColor)tileentity).getColor1() : super.getDamageValue(world, x, y, z);
    }

    public int damageDropped(int damage)
    {
        return damage;
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

	public boolean renderBlockasItem()
	{
		return true;	
	}
	
	public int getRenderType()
	{
		return DecorativeChimneyCore.blockMantelCornerModelID;
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
            return id == DecorativeChimneyCore.blockMantelCorner.blockID;
        }
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
        float var9 = 0.25F;
        float var10 = 0.75F;
        float var11 = 0.25F;
        float var12 = 0.75F;

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

    public static boolean canConnectTo(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int var5 = iblockaccess.getBlockId(i, j, k);

        if (var5 != DecorativeChimneyCore.blockChimneyHollowBricks.blockID && var5 != DecorativeChimneyCore.blockMantelCorner.blockID && var5 != DecorativeChimneyCore.blockMantelCenter.blockID && var5 != DecorativeChimneyCore.blockMantelCenterA.blockID
        		&& var5 != DecorativeChimneyCore.blockMantelSide.blockID && var5 != DecorativeChimneyCore.blockMantelPlainSide.blockID && var5 != DecorativeChimneyCore.blockMantelFoot.blockID && var5 != Block.fenceGate.blockID)
        {
            Block var6 = Block.blocksList[var5];
            return var6 != null && var6.blockMaterial.isOpaque() && var6.renderAsNormalBlock() ? var6.blockMaterial != Material.pumpkin : false;
        }
        else
        {
            return true;
        }
    }

    public static boolean renderMantelCorner(Block block, int i, int j, int k, RenderBlocks renderblocks, IBlockAccess iblockaccess)
    {
        boolean var5 = canConnectTo(renderblocks.blockAccess, i - 1, j, k);
        boolean var6 = canConnectTo(renderblocks.blockAccess, i + 1, j, k);
        boolean var7 = canConnectTo(renderblocks.blockAccess, i, j, k - 1);
        boolean var8 = canConnectTo(renderblocks.blockAccess, i, j, k + 1);
        boolean var9 = var5 && var6 && var7 && var8;
        
        TileEntityColor tileEntityClothColor = (TileEntityColor)iblockaccess.getBlockTileEntity(i, j, k);

        float par1 = 0.0625F;
        float par2 = 0.0625F;
        float par3 = 0.9375F;
        float par4 = 0.9375F;
        float par5 = 0.125F;
        float par6 = 0.125F;
        float par7 = 0.875F;
        float par8 = 0.875F;
        float par9 = 0.1875F;
        float par10 = 0.1875F;
        float par11 = 0.8125F;
        float par12 = 0.8125F;
        float par13 = 0.3125F;
        float par14 = 0.3125F;
        float par15 = 0.6875F;
        float par16 = 0.6875F;
        float par17 = 0.25F;
        float par18 = 0.25F;
        float par19 = 0.75F;
        float par20 = 0.75F;
        
        float par1a = 0.9375F;
        float par1b = 1.0F;
        float par2a = 0.875F;
        float par2b = 0.9375F;
        float par3a = 0.75F;
        float par3b = 0.875F;
        float par4a = 0.6875F;
        float par4b = 0.75F;
        float par5a = 0.5625F;
        float par5b = 0.6875F;
        float par6a = 0.3125F;
        float par6b = 0.5625F;
        float par7a = 0.1875F;
        float par7b = 0.3125F;
        float par8a = 0.125F;
        float par8b = 0.1875F;
        float par9a = 0.0F;
        float par9b = 0.125F;
        float par10a = 0.0F;
        float par10b = 0.6875F;
        
    	for(int l = 0; l < 12; l = l + 2)
    	{
        	for(int m = 1; m < 12; m = m + 2)
    		  {
        		if(iblockaccess.getBlockMetadata(i, j, k) == l || iblockaccess.getBlockMetadata(i, j, k) == m)
        		{
        			if (var9)
        			{
        				renderblocks.overrideBlockTexture = DecorativeChimneyCore.blockMantelCorner.getIcon(6, tileEntityClothColor.getColor1());
        				renderblocks.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		        		renderblocks.renderStandardBlock(block, i, j, k);
        				renderblocks.clearOverrideBlockTexture();
        			}
        			else
        			{
        				int n = l;

    					if(iblockaccess.getBlockMetadata(i, j, k) == m)
    					{
    						n = m;
    						par1a = 0.0F;
    						par1b = 0.0625F;
    						par2a = 0.0625F;
    						par2b = 0.125F;
    						par3a = 0.125F;
    						par3b = 0.25F;
    						par4a = 0.25F;
    						par4b = 0.3125F;
    						par5a = 0.3125F;
    						par5b = 0.4375F;
    						par6a = 0.4375F;
    						par6b = 0.6875F;
    						par7a = 0.6875F;
    						par7b = 0.8125F;
    						par8a = 0.8125F;
    						par8b = 0.875F;
    						par9a = 0.875F;
    						par9b = 1.0F;
    						par10a = 0.3125F;
    						par10b = 1.0F;
    					}
        				if (var5 || var6 || var7 || var8)
        				{
            				renderblocks.overrideBlockTexture = DecorativeChimneyCore.blockMantelCorner.getIcon(6, tileEntityClothColor.getColor1());
        					renderblocks.setRenderBounds(par13, par10a, par14, par15, par10b, par16);
        					renderblocks.renderStandardBlock(block, i, j, k);

        					renderblocks.setRenderBounds(par17, par8a, par18, par19, par8b, par20);
        					renderblocks.renderStandardBlock(block, i, j, k);
            				renderblocks.clearOverrideBlockTexture();
        					
        					if (var5)
        					{
        						par1 = 0.0F;
        						par5 = 0.0F;
        						par9 = 0.0F;
        						par13 = 0.0F;
        						par17 = 0.0F;

        						if (var6)
        						{
        							par3 = 1.0F;
        							par7 = 1.0F;
        							par11 = 1.0F;
        							par15 = 1.0F;
        							par19 = 1.0F;

        							if (var7 && !var8)
        							{
        								par2 = 0.0F;
        								par6 = 0.0F;
        								par10 = 0.0F;
        								par14 = 0.0F;
        								par18 = 0.0F;

        							}
        							else if (var8 && !var7)
        							{
        								par4 = 1.0F;
        								par8 = 1.0F;
        								par12 = 1.0F;
        								par16 = 1.0F;
        								par20 = 1.0F;
        							}
        						}
        						else if (var7 && !var6)
        						{
        							par2 = 0.0F;
        							par6 = 0.0F;
        							par10 = 0.0F;
        							par14 = 0.0F;
        							par18 = 0.0F;

        							if (var8)
        							{
        								par4 = 1.0F;
        								par8 = 1.0F;
        								par12 = 1.0F;
        								par16 = 1.0F;
        								par20 = 1.0F;
        							}
        						}
        						else if (var8 && !var6 && !var7)
        						{
        							par4 = 1.0F;
        							par8 = 1.0F;
        							par12 = 1.0F;
        							par16 = 1.0F;
        							par20 = 1.0F;
        						}
        					}
        					else if (var6 && !var5)
        					{
        						par3 = 1.0F;
        						par7 = 1.0F;
        						par11 = 1.0F;
        						par15 = 1.0F;
        						par19 = 1.0F;

        						if (var7)
        						{
        							par2 = 0.0F;
        							par6 = 0.0F;
        							par10 = 0.0F;
        							par14 = 0.0F;
        							par18 = 0.0F;

        							if(var8)
        							{
        								par4 = 1.0F;
        								par8 = 1.0F;
        								par12 = 1.0F;
        								par16 = 1.0F;
        								par20 = 1.0F;
        							}
        						}
        						else if (var8 && !var7)
        						{
        							par4 = 1.0F;
        							par8 = 1.0F;
        							par12 = 1.0F;
        							par16 = 1.0F;
        							par20 = 1.0F;
        						}
        					}
        					else if (var7 && !var5 && !var6)
        					{
        						par2 = 0.0F;
        						par6 = 0.0F;
        						par10 = 0.0F;
        						par14 = 0.0F;
        						par18 = 0.0F;

        						if(var8)
        						{
        							par4 = 1.0F;
        							par8 = 1.0F;
        							par12 = 1.0F;
        							par16 = 1.0F;
        							par20 = 1.0F;
        						}
        					}
        					else if (var8 && !var5 && !var6 && !var7)
        					{
        						par4 = 1.0F;
        						par8 = 1.0F;
        						par12 = 1.0F;
        						par16 = 1.0F;
        						par20 = 1.0F;
        					}
        				}
		        	}
		        }
        	}
        }
		renderblocks.overrideBlockTexture = DecorativeChimneyCore.blockMantelCorner.getIcon(6, tileEntityClothColor.getColor1());
	//Top
		renderblocks.setRenderBounds(0.0F, par1a, 0.0F, 1.0F, par1b, 1.0F);
		renderblocks.renderStandardBlock(block, i, j, k);

	//Level 2
		renderblocks.setRenderBounds(par1, par2a, par2, par3, par2b, par4);
		renderblocks.renderStandardBlock(block, i, j, k);
	//Level 3
		renderblocks.setRenderBounds(par5, par3a, par6, par7, par3b, par8);
		renderblocks.renderStandardBlock(block, i, j, k);
	//Level 4
		renderblocks.setRenderBounds(par9, par4a, par10, par11, par4b, par12);
		renderblocks.renderStandardBlock(block, i, j, k);

	//Level 5
		renderblocks.setRenderBounds(0.25F, par5a, 0.25F, 0.75F, par5b, 0.75F);
		renderblocks.renderStandardBlock(block, i, j, k);
	//Level 6
	//Pole 1
		renderblocks.setRenderBounds(0.25F, par6a, 0.25F, 0.375F, par6b, 0.375F);
		renderblocks.renderStandardBlock(block, i, j, k);
	//Pole 2
		renderblocks.setRenderBounds(0.625F, par6a, 0.25F, 0.75F, par6b, 0.375F);
		renderblocks.renderStandardBlock(block, i, j, k);
	//Pole 3
		renderblocks.setRenderBounds(0.625F, par6a, 0.625F, 0.75F, par6b, 0.75F);
		renderblocks.renderStandardBlock(block, i, j, k);
	//Pole 4
		renderblocks.setRenderBounds(0.25F, par6a, 0.625F, 0.375F, par6b, 0.75F);
		renderblocks.renderStandardBlock(block, i, j, k);
	//Level 7
		renderblocks.setRenderBounds(0.25F, par7a, 0.25F, 0.75F, par7b, 0.75F);
		renderblocks.renderStandardBlock(block, i, j, k);
	//Level 8
		renderblocks.setRenderBounds(0.1875F, par8a, 0.1875F, 0.8125F, par8b, 0.8125F);
		renderblocks.renderStandardBlock(block, i, j, k);
	//Level 9
		renderblocks.setRenderBounds(0.25F, par9a, 0.25F, 0.75F, par9b, 0.75F);
		renderblocks.renderStandardBlock(block, i, j, k);
		renderblocks.clearOverrideBlockTexture();
	//Core
		renderblocks.overrideBlockTexture = DecorativeChimneyCore.blockMantelCorner.getIcon(7, tileEntityClothColor.getColor2());
		renderblocks.setRenderBounds(0.3115F, par6a, 0.3115F, 0.6885F, par6b, 0.6885F);
		renderblocks.renderStandardBlock(block, i, j, k);
		renderblocks.clearOverrideBlockTexture();

       	block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
       	return true;
	}

    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return true;
    }
    
	@Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityColor();
    }
	
	private Class anEntityClass;
}
