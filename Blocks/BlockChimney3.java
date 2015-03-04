package DecorativeChimney.Blocks;

import java.util.List;
import java.util.Random;

import DecorativeChimney.CommonProxy;
import DecorativeChimney.DecorativeChimneyCore;
import DecorativeChimney.TileEntities.TileEntityChimney3;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockChimney3 extends BlockContainer
{

	public BlockChimney3(int id, Class class1)
	{
		super(id, Material.rock);
		anEntityClass = class1;
		setHardness(5.0F);
    	setResistance(1.0F);
    	setStepSound(Block.soundStoneFootstep);
    	setUnlocalizedName("blockChimney3");
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
    		ItemStack blockChimneyBricksStack = new ItemStack(DecorativeChimneyCore.blockChimney3, 64, i);

    		icons[i] = iconregister.registerIcon(DecorativeChimneyCore.modid + ":" + blockChimneyBricksNames[blockChimneyBricksStack.getItemDamage()]);
    	}
    }
    	
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int meta)
    {
    	return icons[meta];
    }
    
    public static void triggerSmoke(World world, int i, int j, int k, Random random)
    {
        float f = (float)i + 0.5F; //Location Width
        float f1 = (float)j + 0.0F + (random.nextFloat() * 6F) / 10F; //Location Height
        float f2 = (float)k + 0.5F; //Location Length
        float f3 = random.nextFloat() * 0.6F - 0.3F;
        world.spawnParticle("smoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("smoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("smoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("largesmoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("largesmoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("largesmoke", f, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
    }

	public int idDropped(int i, Random par2Random, int j)
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
		return DecorativeChimneyCore.blockChimney3ModelID;
	}
	
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityChimney3();
	}

	private Class anEntityClass;
	
    public boolean getBlocksMovement(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return false;
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int i, int j, int k)
    {
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
    }
    
    public void addCollidingBlockToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }
}