package DecorativeChimney.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecorativeChimney.CommonProxy;
import DecorativeChimney.DecorativeChimneyCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockChimneyHollow extends Block
{

	public BlockChimneyHollow(int id)
	{
    	super(id, Material.rock);
    	setHardness(5.0F);
    	setResistance(1.0F);
    	setStepSound(Block.soundStoneFootstep);
    	setUnlocalizedName("blockChimneyHollowBricks");
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

    private static final String[] blockChimneyBricksNames =
		{ 
			"BlackMarble", "GrayMarble", "WhiteMarble", "BlackLargeBrick", "BlackSmallBrick", "StoneLargeBrick", "StoneSmallBrick", "Stone",
			"CobbleStone", "WhiteLargeBrick", "WhiteSmallBrick", "NetherBrick", "Brick", "Emerald", "Gold", "Diamond"
		};

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
    	icons = new Icon[16];
    	
    	for(int i = 0; i < icons.length; i++)
    	{
    		ItemStack blockChimneyBricksStack = new ItemStack(DecorativeChimneyCore.blockChimneyHollowBricks, 64, i);

    		icons[i] = iconregister.registerIcon(DecorativeChimneyCore.modid + ":" + blockChimneyBricksNames[blockChimneyBricksStack.getItemDamage()]);
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

	public boolean renderBlockasItem()
	{
		return true;	
	}
	
	public int getRenderType()
	{
		return DecorativeChimneyCore.blockHollowBricksModelID;
	}

	public String getTextureFile()
	{
		return "/DecorativeChimney/Textures/ChimneyItems.png";
	}
   
    public int getMobilityFlag()
    {
		return 0;    
    }

	public void randomDisplayTick(World world, int i, int j, int k, Random random)
	{
		int var1 = Block.furnaceBurning.blockID;
		int var2 = DecorativeChimneyCore.blockLogsOn.blockID;
		int var3 = Block.fire.blockID;
		int var4 = world.getBlockId(i - 1, j, k);
		int var5 = world.getBlockId(i + 1, j, k);
		int var6 = world.getBlockId(i, j, k - 1);
		int var7 = world.getBlockId(i, j, k + 1);
		int var8 = world.getBlockId(i, j - 1, k);
		int var9 = world.getBlockId(i, j - 2, k);

        if (var4 == var1 || var5 == var1 || var6 == var1 || var7 == var1 || var8 == var1 ||
        		var4 == var2 || var5 == var2 || var6 == var2 || var7 == var2 || var8 == var2 ||
                var4 == var3 || var5 == var3 || var6 == var3 || var7 == var3 || var8 == var3 ||
        		var9 == var2 || var9 == var3 || world.isBlockIndirectlyGettingPowered(i, j, k))
        {
            triggerSmoke(world, i, j - 1, k, random);
        }
	}

    public static void triggerSmoke(World world, int i, int j, int k, Random random)
    {
    	int var1 = world.getBlockId(i, j + 1, k);
        if (var1 == DecorativeChimneyCore.blockChimneyHollowBricks.blockID)
        {
            triggerSmoke(world, i, j + 1, k, random);
        }
        else if (var1 == DecorativeChimneyCore.blockChimney1.blockID)
        {
            BlockChimney1.triggerSmoke(world, i, j + 1, k, random);
        }
        else if (var1 == DecorativeChimneyCore.blockChimney2.blockID || var1 == DecorativeChimneyCore.blockChimney2A.blockID ||
        		var1 == DecorativeChimneyCore.blockChimney2B.blockID || var1 == DecorativeChimneyCore.blockChimney2C.blockID)
        {
            BlockChimney2.triggerSmoke(world, i, j + 1, k, random);
        }
        else if (var1 == DecorativeChimneyCore.blockChimney3.blockID)
        {
            BlockChimney3.triggerSmoke(world, i, j + 1, k, random);
        }
        else
        {
        		float f = (float)i + 0.5F; //Location Width
        		float f1 = (float)j + 0.75F + (random.nextFloat() * 6F) / 10F; //Location Height
        		float f2 = (float)k + 0.5F; //Location Length
        		float f3 = random.nextFloat() * 0.6F - 0.3F;
        		world.spawnParticle("smoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        		world.spawnParticle("smoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        		world.spawnParticle("smoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        		world.spawnParticle("largesmoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        		world.spawnParticle("largesmoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        		world.spawnParticle("largesmoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        }
    }

    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
    {
		return true;
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void addCollidingBlockToList(World world, int i, int j, int k, AxisAlignedBB axisAlignedBB, List list, Entity entity)
    {
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, i, j, k, axisAlignedBB, list, entity);
    }

    public static boolean renderHollowBricks(Block block, int i, int j, int k, RenderBlocks renderblocks, IBlockAccess iblockaccess)
    {
    	renderblocks.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
    	renderblocks.renderStandardBlock(block, i, j, k);

    	renderblocks.setRenderBounds(0.875F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
    	renderblocks.renderStandardBlock(block, i, j, k);


    	renderblocks.setRenderBounds(0.0F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
    	renderblocks.renderStandardBlock(block, i, j, k);

    	renderblocks.setRenderBounds(0.0F, 0.0F,0.125F, 0.125F, 1.0F, 0.875F);
    	renderblocks.renderStandardBlock(block, i, j, k);

       	renderblocks.clearOverrideBlockTexture();
       	block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
       	return true;
	}

    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return true;
    }
}
